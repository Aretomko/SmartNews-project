package com.example.application.dataProvider;

import com.thinhda.spring.grpc.core.domain.LoginRequest;
import com.thinhda.spring.grpc.core.domain.LoginResponse;
import com.thinhda.spring.grpc.core.domain.LoginServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDataProvider{
    public String authenticate(String username, String password){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();

        LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginRequest request = LoginRequest.newBuilder().setUsername(username).setPassword(password).build();

        LoginResponse response = stub.login(request);

        channel.shutdownNow();

        return response.getToken();
    }
}
