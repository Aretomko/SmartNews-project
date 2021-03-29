package com.example.smartnews;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.thinhda.spring.grpc.core.model.LoginRequest;
import com.thinhda.spring.grpc.core.model.LoginResponse;
import com.thinhda.spring.grpc.core.model.LoginServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginResponse response = getUserExample();
    }

    public LoginResponse getUserExample(){

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();

        LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginRequest request = LoginRequest.newBuilder().setUsername("username").setPassword("Password").build();

        LoginResponse response = stub.login(request);

        TextView textView = (TextView) findViewById(R.id.text);

        textView.setText(response.getToken());

        channel.shutdownNow();

        return response;
    }
}