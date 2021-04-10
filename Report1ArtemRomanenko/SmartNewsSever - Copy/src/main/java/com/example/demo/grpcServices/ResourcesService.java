package com.example.demo.grpcServices;

import com.example.demo.repos.ResourceRepo;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.List;

@GRpcService
public class ResourcesService extends ResourcesServiceGrpc.ResourcesServiceImplBase {
    private final ResourceRepo resourceRepo;

    public ResourcesService(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    @Override
    public void getAllResources(GetAllResourcesRequest request, StreamObserver<AllResources> responseObserver) {
        List<com.example.demo.domain.Resource> resources = resourceRepo.findAll();
        AllResources.Builder response = AllResources.newBuilder();
        for (int i=0; i<resources.size(); i++){
            Resource resource = Resource.newBuilder()
                    .setName(resources.get(i).getName())
                    .setReference(resources.get(i).getReference())
                    .build();
            response.addResource(i, resource);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getResourceById(GetResourceByIdRequest request, StreamObserver<Resource> responseObserver) {
        Long id = Long.valueOf(request.getId());
        com.example.demo.domain.Resource resource = resourceRepo.findById(id).get();
        if(resource ==null) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
            return;
        }
        Resource response = Resource.newBuilder()
                .setName(resource.getName())
                .setReference(resource.getReference())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createNewResource(CreateResourceRequest request, StreamObserver<CreateResourceResponse> responseObserver) {
        com.example.demo.domain.Resource resource = new com.example.demo.domain.Resource(request.getName(), request.getReference());
        resourceRepo.save(resource);
        CreateResourceResponse response = CreateResourceResponse.newBuilder()
                .setCreated(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void editResource(EditResourceRequest request, StreamObserver<EditResourceResponse> responseObserver) {
        com.example.demo.domain.Resource resource  =  resourceRepo.findById(Long.parseLong(request.getId())).get();
        if (resource==null){
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
            return;
        }else {
            resource.setName(request.getName());
            resource.setReference(request.getReference());
            resourceRepo.save(resource);
            EditResourceResponse response = EditResourceResponse.newBuilder()
                    .setUpdated(true)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
