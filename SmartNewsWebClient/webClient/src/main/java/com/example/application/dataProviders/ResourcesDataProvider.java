package com.example.application.dataProviders;

import com.example.application.domain.Resource;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesDataProvider {
    public List<Resource> getAllResources(){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();

        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub = ResourcesServiceGrpc.newBlockingStub(channel);

        GetAllResourcesRequest request = GetAllResourcesRequest.newBuilder().setToken("token").build();

        AllResources response = stub.getAllResources(request);

        channel.shutdownNow();

        List<Resource> resources = new ArrayList<>();

        for(int i=0; i<response.getResourceCount(); i++) {
            resources.add(new Resource(response.getResource(i).getName(), response.getResource(i).getReference()));
        }

        return resources;
    }
}
