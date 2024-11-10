package com.bacsystem.grpc.services;


import com.bacsystem.grpc.components.enums.Operator;
import com.bacsystem.grpc.components.utils.StringUtility;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.StringUtils;
import proto.HelloOuterClass;
import proto.QueryDataServiceGrpc;

import java.util.Comparator;
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

    @Override
    public void executeTransactionQuery(HelloOuterClass.TransactionQueryRequest request, StreamObserver<HelloOuterClass.TransactionQueryResponse> responseObserver) {
        int pageSize = 200_000;
        var arg = filter(request);

        super.executeTransactionQuery(request, responseObserver);
    }

    private String filter(HelloOuterClass.TransactionQueryRequest request) {
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
}
