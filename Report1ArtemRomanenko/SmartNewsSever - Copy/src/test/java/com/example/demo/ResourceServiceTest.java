package com.example.demo;

import com.example.demo.domain.Resource;
import com.example.demo.grpcServices.ResourcesService;
import com.example.demo.repos.ResourceRepo;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class ResourceServiceTest {
    @Autowired
    private ResourceRepo resourceRepo;

    @Test
    public void getAllResources_CorrectRequest_AllResourcesListReturned(){
        GetAllResourcesRequest request = GetAllResourcesRequest.newBuilder()
                .setToken("token")
                .build();

        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        AllResources response = stub.getAllResources(request);
        Assert.assertEquals(response.getResourceCount(), resourceRepo.findAll().size());
    }
    @Test(expected = io.grpc.StatusRuntimeException.class)
    public void getAllResources_TokenDoesNotExist_ExceptionReceived(){
        GetAllResourcesRequest request = GetAllResourcesRequest.newBuilder()
                .setToken("tokedfdfsdf3n")
                .build();
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        AllResources response = stub.getAllResources(request);
    }
    @Test
    public void createNewResource_CorrectResponse_NewResponsesCreated(){
        CreateResourceRequest request = CreateResourceRequest.newBuilder()
                .setName("New name")
                .setReference("Reference")
                .build();
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        CreateResourceResponse response = stub.createNewResource(request);
        Assert.assertTrue(response.getCreated());
    }
    @Test
    public void editResource_CorrectRequest_ResourceNameEdited(){
    EditResourceRequest request = EditResourceRequest.newBuilder()
                .setName("new name")
                .setReference("reference")
                .setId("26")
                .build();
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        EditResourceResponse response = stub.editResource(request);
        Resource resource= resourceRepo.findById(26L).get();
        Assert.assertTrue(resource.getName().equals("new name"));
    }
    @Test
    public void editResource_CorrectRequest_ResourceReferenceEdited(){
        EditResourceRequest request = EditResourceRequest.newBuilder()
                .setName("new name")
                .setReference("reference")
                .setId("26")
                .build();
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        EditResourceResponse response = stub.editResource(request);
        Resource resource= resourceRepo.findById(26L).get();
        Assert.assertTrue(resource.getReference().equals("reference"));
    }
    @Test
    public void getResourceById(){
        GetResourceByIdRequest request = GetResourceByIdRequest.newBuilder()
                .setId("26")
                .build();
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        ResourcesServiceGrpc.ResourcesServiceBlockingStub stub= ResourcesServiceGrpc.newBlockingStub(channel);
        com.thinhda.spring.grpc.core.model.Resource response =stub.getResourceById(request);
        Resource resource = resourceRepo.findById(26l).get();
        Assert.assertTrue(resource.getReference().equals(response.getReference()));
    }
}
