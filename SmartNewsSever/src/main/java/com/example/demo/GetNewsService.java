package com.example.demo;

import com.thinhda.spring.grpc.core.model.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class GetNewsService extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void getNewsByCategory(GetNewsByCategoryRequest request, StreamObserver<MultipleNewsResponse> responseObserver) {
       String categoryName = request.getCategory();

    }

    @Override
    public void getNewsById(GetNewsByIdRequest request, StreamObserver<SingleNewsResponse> responseObserver) {

    }

    @Override
    public void getAllNews(GetAllNewsRequest request, StreamObserver<MultipleNewsResponse> responseObserver) {
        super.getAllNews(request, responseObserver);
    }

    @Override
    public void getAllCategories(GetCategoriesRequest request, StreamObserver<MultipleCategoriesResponse> responseObserver) {
        super.getAllCategories(request, responseObserver);
    }

    @Override
    public void getSourcesByNews(GetSourcesByNewsRequest request, StreamObserver<MultipleSourcesResponse> responseObserver) {
        super.getSourcesByNews(request, responseObserver);
    }
}
