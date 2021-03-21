package com.example.demo;


import com.example.grpc.LoginServiceGrpc;
import com.example.grpc.LoginServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;

@GRpcService
public class LoginServiceImpl extends LoginServiceGrpc.LoginServiceImplBase {
    @Override
    public void login(com.example.grpc.LoginServiceOuterClass.LoginRequest loginRequest,
                      StreamObserver<LoginServiceOuterClass.LoginResponse> responseObserver){
        com.example.grpc.LoginServiceOuterClass.LoginResponse response = com.example.grpc.LoginServiceOuterClass.LoginResponse
                .newBuilder().setToken(UUID.randomUUID().toString()).build();
        responseObserver.onNext(response);
    }
}
