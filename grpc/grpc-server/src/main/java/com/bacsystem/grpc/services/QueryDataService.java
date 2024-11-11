package com.bacsystem.grpc.services;


import com.bacsystem.grpc.components.enums.Operator;
import com.bacsystem.grpc.components.utils.StringUtility;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import proto.HelloOuterClass;
import proto.QueryDataServiceGrpc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <b>QueryDataService</b>
 * <p>
 * This class specifies the requirements for the {@link QueryDataService} component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 10/11/2024
 */

@Log4j2
@GrpcService
public class QueryDataService extends QueryDataServiceGrpc.QueryDataServiceImplBase {

    private final JdbcTemplate jdbcTemplate;

    public QueryDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void executeTransactionQuery(HelloOuterClass.TransactionQueryRequest request, StreamObserver<HelloOuterClass.TransactionQueryResponse> responseObserver) {
        log.info("start transaction query from client with request: {}", request);

        int pageSize = 200_000;

        var statement = statementFilter(request);

        var countSql = String.format("SELECT COUNT(*) FROM %s %s", StringUtility.replaceSql(request.getNameView()), statement);

        var arg = argumentFilter(request);

        var totalItems = ObjectUtils.defaultIfNull(jdbcTemplate.queryForObject(countSql, (rs, rowNum) -> rs.getInt(1), arg.toArray()), 0);

        var totalPages = Math.ceil((double) totalItems / pageSize);
        log.info("Total items [{}] Total pages [{}]", totalItems, totalPages);



        log.info("Count sql [{}]", countSql);

        log.info("statement: {}", statement);


        super.executeTransactionQuery(request, responseObserver);
    }

    private String statementFilter(HelloOuterClass.TransactionQueryRequest request) {
        String statementFilter = StringUtils.EMPTY;
        var fieldFilter = request.getTransactionQueryFilterList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryFilter::getMappingSqlModel))
                .map(transactionQueryFilter -> {
                    String model = StringUtility.replaceSql(transactionQueryFilter.getMappingSqlModel());
                    var operator = Operator.getOperator(transactionQueryFilter.getOperator()).getSymbol();
                    return String.format("%s %s ?", model, operator);
                }).collect(Collectors.joining(" AND "));

        log.info("statement {}", statementFilter);
        log.info("filter {}", fieldFilter);

        if (StringUtils.isNotBlank(fieldFilter)) {
            statementFilter = String.format(" WHERE %s", fieldFilter);
        }

        log.info("statement {}", statementFilter);
        return statementFilter;

    }

    private ArrayList<Object> argumentFilter(HelloOuterClass.TransactionQueryRequest request) {
        return new ArrayList<>(request.getTransactionQueryFilterList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryFilter::getOrder))
                .map(HelloOuterClass.TransactionQueryFilter::getValue)
                .map(StringUtility::replaceSql)
                .toList());
    }


    private Page<Map<String, String>> executeQuery(HelloOuterClass.TransactionQueryRequest request, Pageable pageable) {
        log.info("Execute query on View [{}] on page [{}]", request.getNameView(), pageable.getPageNumber());
        List<Map<String, String>> dataCollection = new ArrayList<>();
        var statement = statementFilter(request);

        var fieldQuery = request.getTransactionQueryDetailList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryDetail::getOrder))
                .map(transactionQueryDetail -> String.format("%s AS %s", transactionQueryDetail.getMappingSqlModel(), transactionQueryDetail.getName()))
                .collect(Collectors.joining(", "));

        var rawQuery = String.format("SELECT * FROM (SELECT m.*, ROWNUM rnum FROM (SELECT %s FROM %s %s ) m WHERE ROWNUM <= ?) WHERE rnum > ?", fieldQuery, request.getNameView(), statement);
        log.info("Fields query [{}]", fieldQuery);
        log.info("Raw query [{}]", rawQuery);

        var arg = argumentFilter(request);
        log.info("arg [{}]", arg);
        arg.add(pageable.getOffset() + pageable.getPageSize());
        arg.add(pageable.getOffset());

        jdbcTemplate.setFetchSize(pageable.getPageSize());
        dataCollection.addAll(jdbcTemplate.query(rawQuery, (rs, rowNum) -> {
            Map<String, String> dataMap = new HashMap<>();
            for (var transactionQueryDetail : request.getTransactionQueryDetailList()) {
                dataMap.put(transactionQueryDetail.getName(), rs.getString(transactionQueryDetail.getName()));
            }
            return dataMap;
        }, arg.toArray()));

        log.info("Data collection size [{}]", dataCollection.size());
        return new PageImpl<>(dataCollection, pageable, dataCollection.size());
    }
}
