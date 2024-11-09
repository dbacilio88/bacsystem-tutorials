package com.bacsystem.grpc.services;


import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import proto.HelloOuterClass;
import proto.HelloServiceGrpc;

/**
 * <b>HelloService</b>
 * <p>
 * This class specifies the requirements for the {@link HelloService} component,
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
 * @since 8/11/2024
 */

@Log4j2
@GrpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloOuterClass.HelloRequest request, StreamObserver<HelloOuterClass.HelloResponse> responseObserver) {

        log.info("start hello from client with request: {}", request);
        final String message = "hello " + request.getHello().getPrefix() + ", " + request.getHello().getFirstName();
        responseObserver.onNext(HelloOuterClass.HelloResponse.newBuilder()
                .setCustomHello(message)
                .build());
        responseObserver.onCompleted();
    }
}
