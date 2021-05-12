package com.example.application.dataProvider;

import com.example.application.domain.domain.Resource;
import com.thinhda.spring.grpc.core.domain.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesDataProvider {

    private final ManagedChannel channel;
    private final ResourcesServiceGrpc.ResourcesServiceBlockingStub stub;

    public ResourcesDataProvider() {
        this.channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        this.stub = ResourcesServiceGrpc.newBlockingStub(this.channel);

    }

    public List<Resource> getAllResources(){

        GetAllResourcesRequest request = GetAllResourcesRequest.newBuilder()
                .setToken("token").build();

        AllResources response = stub.getAllResources(request);

        List<Resource> resources = new ArrayList<>();

        for(int i=0; i<response.getResourceCount(); i++) {
            resources.add(new Resource(response.getResource(i).getName(), response.getResource(i).getReference()));
        }

        return resources;
    }

    public boolean createResource(String name, String reference){
        CreateResourceRequest request = CreateResourceRequest.newBuilder().setName(name).setReference(reference).build();

        CreateResourceResponse response = stub.createNewResource(request);

        return response.getCreated();
    }

    public boolean editResource(String id, String name, String reference){
        EditResourceRequest request = EditResourceRequest.newBuilder().setId(id).setName(name).setReference(reference).build();

        EditResourceResponse response = stub.editResource(request);

        return response.getUpdated();
    }
}
