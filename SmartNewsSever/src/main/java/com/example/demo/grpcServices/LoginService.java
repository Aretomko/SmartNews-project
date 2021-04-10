package com.example.demo.grpcServices;

import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import com.thinhda.spring.grpc.core.model.LoginRequest;
import com.thinhda.spring.grpc.core.model.LoginResponse;
import com.thinhda.spring.grpc.core.model.LoginServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;

@GRpcService
public class LoginService extends LoginServiceGrpc.LoginServiceImplBase {
    private final UserRepo userRepo;

    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        User user = userRepo.findByUsername(request.getUsername());
        LoginResponse.Builder responseBuilder = LoginResponse.newBuilder();
        if (user!=null&& user.getPassword().equals(request.getPassword())) {
            String uuid = UUID.randomUUID().toString();
            user.setToken(uuid) ;
            userRepo.save(user);
            responseBuilder.setToken(uuid);
        }else{
            responseBuilder.setToken("denied");
        }
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
