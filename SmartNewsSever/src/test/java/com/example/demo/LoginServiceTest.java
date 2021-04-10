package com.example.demo;

import com.thinhda.spring.grpc.core.model.LoginRequest;
import com.thinhda.spring.grpc.core.model.LoginResponse;
import com.thinhda.spring.grpc.core.model.LoginServiceGrpc;
import com.thinhda.spring.grpc.core.model.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class LoginServiceTest {
    @Test
    public void login_rightCredentials_UUIDReturned(){
        LoginRequest request = LoginRequest.newBuilder()
                .setUsername("admin")
                .setPassword("123")
                .build();
        ManagedChannel channel;
        LoginServiceGrpc.LoginServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginResponse response = stub.login(request);
        Assert.assertTrue(!response.getToken().equals("denied"));
    }
    @Test
    public void login_UsernameDoNotExist_UUIDReturned(){
        LoginRequest request = LoginRequest.newBuilder()
                .setUsername("a2342342342423")
                .setPassword("123")
                .build();
        ManagedChannel channel;
        LoginServiceGrpc.LoginServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginResponse response = stub.login(request);
        Assert.assertTrue(response.getToken().equals("denied"));
    }
    @Test
    public void login_WrongPassword_UUIDReturned(){
        LoginRequest request = LoginRequest.newBuilder()
                .setUsername("admin")
                .setPassword("12323342342342424")
                .build();
        ManagedChannel channel;
        LoginServiceGrpc.LoginServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginResponse response = stub.login(request);
        Assert.assertTrue(response.getToken().equals("denied"));
    }
}
