package com.example.application.dataProviders;

import com.thinhda.spring.grpc.core.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class DataProvider {
    public String authenticate(String username, String password){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();

        LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginRequest request = LoginRequest.newBuilder().setUsername("username").setPassword("Password").build();

        LoginResponse response = stub.login(request);

        channel.shutdownNow();

        return response.getToken();
    }
    public boolean createFollowPost(String token){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();

        CreatePostServiceGrpc.CreatePostServiceBlockingStub stub = CreatePostServiceGrpc.newBlockingStub(channel);

        CreateFollowPostRequest request = CreateFollowPostRequest.newBuilder().setToken("lol").build();

        CreatePostResponse response = stub.createFollowPost(request);

        channel.shutdownNow();

        return  response.getCreated();

    }
    public boolean createStarPost(String token){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();

        CreatePostServiceGrpc.CreatePostServiceBlockingStub stub = CreatePostServiceGrpc.newBlockingStub(channel);

        CreateStarRepoPostRequest repoPostRequest = CreateStarRepoPostRequest.newBuilder().setToken(token).build();

        CreatePostResponse response = stub.createStarRepoPost(repoPostRequest);

        channel.shutdownNow();

        return response.getCreated();
    }
}
