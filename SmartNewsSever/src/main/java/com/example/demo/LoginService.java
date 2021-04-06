package com.example.demo;

import com.thinhda.spring.grpc.core.model.LoginRequest;
import com.thinhda.spring.grpc.core.model.LoginResponse;
import com.thinhda.spring.grpc.core.model.LoginServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;

@GRpcService
public class LoginService extends LoginServiceGrpc.LoginServiceImplBase {
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        LoginResponse response = LoginResponse.newBuilder()
                .setToken(UUID.randomUUID().toString())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
