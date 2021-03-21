package com.example.smartnews;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.grpc.LoginServiceGrpc;
import com.example.grpc.LoginServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081").usePlaintext().build();

        LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginServiceOuterClass.LoginRequest request = LoginServiceOuterClass.LoginRequest.newBuilder().setLogin("login").setPassword("password").build();

        LoginServiceOuterClass.LoginResponse response = stub.login(request);

        TextView textView = (TextView) findViewById(R.id.text);

        textView.setText(response.getToken());

        channel.shutdownNow();

    }

}