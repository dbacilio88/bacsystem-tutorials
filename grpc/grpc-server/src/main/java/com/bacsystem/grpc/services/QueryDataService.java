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
import org.springframework.data.domain.PageRequest;
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
    private static final int PAGE_SIZE = 200_000;

    public QueryDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void executeTransactionQuery(HelloOuterClass.TransactionQueryRequest request, StreamObserver<HelloOuterClass.TransactionQueryResponse> responseObserver) {
        log.info("start transaction query from client with request: {}", request);

        try {
            final String statement = statementFilter(request);

            final String countSql = String.format("SELECT COUNT(*) FROM %s %s", StringUtility.replaceSql(request.getNameView()), statement);
            log.info("countSql {}", countSql);
            final List<Object> arg = argumentFilter(request);

            final int totalItems = ObjectUtils.defaultIfNull(
                    jdbcTemplate.queryForObject(countSql, (rs, rowNum) -> rs.getInt(1), arg.toArray()), 0);

            final int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
            log.info("Total items [{}] Total pages [{}]", totalItems, totalPages);

            for (int i = 0; i < totalPages; i++) {
                log.info("i {},totalPages {}", i, totalPages);

                final List<HelloOuterClass.TransactionQueryResponse> response = executeQuery(request, PageRequest.of(i, PAGE_SIZE)).getContent()
                        .stream()
                        .parallel()
                        .map(this::mapToTransactionQueryResponse)
                        .toList();

                for (HelloOuterClass.TransactionQueryResponse r : response) {
                    responseObserver.onNext(r);
                }
            }
        } catch (Exception e) {
            log.error("Error during transaction query execution", e);
            responseObserver.onError(e);
        } finally {
            responseObserver.onCompleted();
        }
    }

    private HelloOuterClass.TransactionQueryResponse mapToTransactionQueryResponse(Map<String, String> dataMap) {
        HelloOuterClass.TransactionQueryResponse.Builder responseBuilder = HelloOuterClass.TransactionQueryResponse.newBuilder();
        dataMap.forEach((key, value) -> {
            responseBuilder.addRecordDetail(
                    HelloOuterClass.RecordDetail.newBuilder()
                            .setKey(key)
                            .setValue(Objects.toString(value, StringUtils.EMPTY))
                            .build()
            );
        });
        return responseBuilder.build();
    }

    private String statementFilter(HelloOuterClass.TransactionQueryRequest request) {
        String filterClause = request.getTransactionQueryFilterList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryFilter::getMappingSqlModel))
                .map(filter -> String.format("%s %s ?",
                        StringUtility.replaceSql(filter.getMappingSqlModel()),
                        Operator.getOperator(filter.getOperator()).getSymbol()))
                .collect(Collectors.joining(" AND "));
        return StringUtils.isNotBlank(filterClause) ? " WHERE " + filterClause : StringUtils.EMPTY;
    }

    private List<Object> argumentFilter(HelloOuterClass.TransactionQueryRequest request) {
        return request.getTransactionQueryFilterList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryFilter::getOrder))
                .map(HelloOuterClass.TransactionQueryFilter::getValue)
                .map(StringUtility::replaceSql)
                .collect(Collectors.toList());
    }

    private String getParameterAs(HelloOuterClass.TransactionQueryRequest request) {
        return request.getTransactionQueryDetailList()
                .stream()
                .sorted(Comparator.comparing(HelloOuterClass.TransactionQueryDetail::getOrder))
                .map(transactionQueryDetail -> String.format("%s AS %s", transactionQueryDetail.getMappingSqlModel(), transactionQueryDetail.getName()))
                .collect(Collectors.joining(", "));
    }


    private Page<Map<String, String>> executeQuery(HelloOuterClass.TransactionQueryRequest request, Pageable pageable) {
        log.info("Execute query on View [{}] on page [{}]", request.getNameView(), pageable.getPageNumber());

        List<Map<String, String>> dataCollection = new ArrayList<>();

        String statement = statementFilter(request);

        final String query = getParameterAs(request);
        log.info("query {}", query);


        // var rawQuery = String.format("SELECT * FROM (SELECT m.*, ROWNUM rnum FROM (SELECT %s FROM %s %s ) m WHERE ROWNUM <= ?) WHERE rnum > ?", query, request.getNameView(), statement);

        // final String query = "SELECT * FROM (SELECT %s FROM %s %s) LIMIT ? OFFSET ?";
        var rawQuery = String.format("SELECT * FROM (SELECT %s FROM %s %s) LIMIT ? OFFSET ?", query, request.getNameView(), statement);

        log.info("Fields query [{}]", query);
        log.info("Raw query [{}]", rawQuery);

        var arg = argumentFilter(request);


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
