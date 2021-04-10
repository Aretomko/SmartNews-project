package com.example.demo.grpcServices;

import com.example.demo.AuthenticationService;
import com.example.demo.domain.Category;
import com.example.demo.domain.News;
import com.example.demo.repos.CategoryRepo;
import com.example.demo.repos.NewsRepo;
import com.example.demo.repos.SourceRepo;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.List;
import java.util.Objects;

@GRpcService
public class GetNewsService extends NewsServiceGrpc.NewsServiceImplBase {
    private final CategoryRepo categoryRepo;
    private final NewsRepo newsRepo;
    private final SourceRepo sourceRepo;
    private final AuthenticationService authenticationService;

    public GetNewsService(CategoryRepo categoryRepo,
                          NewsRepo newsRepo,
                          SourceRepo sourceRepo,
                          AuthenticationService authenticationService) {
        this.categoryRepo = categoryRepo;
        this.newsRepo = newsRepo;
        this.sourceRepo = sourceRepo;
        this.authenticationService = authenticationService;
    }

    @Override
    public void getNewsByCategory(GetNewsByCategoryRequest request, StreamObserver<MultipleNewsResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        } else {

            String categoryName = request.getCategory();
            Category category = categoryRepo.findByName(categoryName);

            List<News> news = category.getNews();

            MultipleNewsResponse.Builder responseBuilder = MultipleNewsResponse.newBuilder();
            for (int i = 0; i < news.size(); i++) {
                com.thinhda.spring.grpc.core.model.News.Builder newsGrpcBuilder = com.thinhda.spring.grpc.core.model.News.newBuilder()
                        .setId(String.valueOf(news.get(i).getId()))
                        .setHeading(news.get(i).getHeading());
                for (int j = 0; j < news.get(i).getSources().size(); j++) {
                    com.example.demo.domain.Source source = news.get(i).getSources().get(j);
                    com.thinhda.spring.grpc.core.model.Source sourceGrpc = Source.newBuilder()
                            .setId(String.valueOf(source.getId()))
                            .setName(source.getName())
                            .setReference(source.getReference())
                            .setLikes(source.getLikes())
                            .build();
                    newsGrpcBuilder.addSources(j, sourceGrpc);
                }
                com.thinhda.spring.grpc.core.model.News newsGrpc = newsGrpcBuilder.build();
                responseBuilder.addNews(i, newsGrpc);
            }
            MultipleNewsResponse response = responseBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }
    }

    @Override
    public void getNewsById(GetNewsByIdRequest request, StreamObserver<SingleNewsResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String newsId = request.getId();
            Long longId = Long.valueOf(newsId.trim());
            News news = newsRepo.findById(longId).orElse(null);

            com.thinhda.spring.grpc.core.model.Category categoryGrpc = com.thinhda.spring.grpc.core.model.Category.newBuilder()
                    .setId(String.valueOf(news.getCategory().getId()))
                    .setName(news.getCategory().getName())
                    .build();

            com.thinhda.spring.grpc.core.model.News.Builder newsGrpc = com.thinhda.spring.grpc.core.model.News.newBuilder()
                    .setId(String.valueOf(news.getId()))
                    .setHeading(news.getHeading())
                    .setCategory(categoryGrpc);

            for (int i = 0; i < news.getSources().size(); i++) {
                com.example.demo.domain.Source source = news.getSources().get(i);
                com.thinhda.spring.grpc.core.model.Source sourceGrpc = Source.newBuilder()
                        .setId(String.valueOf(source.getId()))
                        .setName(source.getName())
                        .setReference(source.getReference())
                        .setLikes(source.getLikes())
                        .build();
                newsGrpc.addSources(i, sourceGrpc);
            }

            SingleNewsResponse response = SingleNewsResponse.newBuilder()
                    .setNews(newsGrpc.build())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAllNews(GetAllNewsRequest request, StreamObserver<MultipleNewsResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            List<News> news = newsRepo.findAll();
            MultipleNewsResponse.Builder responseBuilder = MultipleNewsResponse.newBuilder();
            for (int i = 0; i < news.size(); i++) {
                com.thinhda.spring.grpc.core.model.News.Builder newsGrpcBuilder = com.thinhda.spring.grpc.core.model.News.newBuilder()
                        .setId(String.valueOf(news.get(i).getId()))
                        .setHeading(news.get(i).getHeading());
                for (int j = 0; j < news.get(i).getSources().size(); j++) {
                    com.example.demo.domain.Source source = news.get(i).getSources().get(j);
                    com.thinhda.spring.grpc.core.model.Source sourceGrpc = Source.newBuilder()
                            .setId(String.valueOf(source.getId()))
                            .setName(source.getName())
                            .setReference(source.getReference())
                            .setLikes(source.getLikes())
                            .build();
                    newsGrpcBuilder.addSources(j, sourceGrpc);
                }
                com.thinhda.spring.grpc.core.model.News newsGrpc = newsGrpcBuilder.build();
                responseBuilder.addNews(i, newsGrpc);
            }
            MultipleNewsResponse response = responseBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAllCategories(GetCategoriesRequest request, StreamObserver<MultipleCategoriesResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            List<Category> categories = categoryRepo.findAll();

            MultipleCategoriesResponse.Builder responseBuilder = MultipleCategoriesResponse.newBuilder();
            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                com.thinhda.spring.grpc.core.model.Category categoryGrpc = com.thinhda.spring.grpc.core.model.Category.newBuilder()
                        .setId(String.valueOf(category.getId()))
                        .setName(category.getName())
                        .build();
                responseBuilder.addCategory(i, categoryGrpc);
            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getSourcesByNews(GetSourcesByNewsRequest request, StreamObserver<MultipleSourcesResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String newsId = request.getNewsId();
            Long longId = Long.parseLong(newsId.trim());
            News news = newsRepo.findById(longId).orElse(null);

            List<com.example.demo.domain.Source> sources = Objects.requireNonNull(news).getSources();

            MultipleSourcesResponse.Builder responseBuilder = MultipleSourcesResponse.newBuilder();
            for (int i = 0; i < sources.size(); i++) {
                com.example.demo.domain.Source source = sources.get(i);
                Source sourceGrpc = Source.newBuilder()
                        .setId(String.valueOf(source.getId()))
                        .setReference(source.getReference())
                        .setName(source.getName())
                        .setLikes(source.getLikes())
                        .build();
                responseBuilder.addSource(i, sourceGrpc);
            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
    @Override
    public void deleteCategory(DeleteCategoryRequest request, StreamObserver<DeleteResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String categoryId = request.getCategoryId();
            DeleteResponse.Builder responseBuilder = DeleteResponse.newBuilder();
            try {
                categoryRepo.deleteById(Long.valueOf(categoryId.trim()));
            } catch (Exception ex) {
                responseBuilder.setDeleted(false);
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            }
            responseBuilder.setDeleted(true);
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
    @Override
    public void deleteSource(DeleteSourceRequest request, StreamObserver<DeleteResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String sourceId = request.getSourceId();
            DeleteResponse.Builder responseBuilder = DeleteResponse.newBuilder();
            try {
                sourceRepo.deleteById(Long.valueOf(sourceId.trim()));
            } catch (Exception ex) {
                responseBuilder.setDeleted(false);
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            }
            responseBuilder.setDeleted(true);
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteNews(DeleteNewsRequest request, StreamObserver<DeleteResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String newsId = request.getNewsId();
            DeleteResponse.Builder responseBuilder = DeleteResponse.newBuilder();
            try {
                newsRepo.deleteById(Long.valueOf(newsId.trim()));
            } catch (Exception ex) {
                responseBuilder.setDeleted(false);
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            }
            responseBuilder.setDeleted(true);
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void createCategory(CreateCategoryRequest request, StreamObserver<CreateResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String categoryName = request.getName();
            Category category = new Category(categoryName);
            categoryRepo.save(category);
            CreateResponse response = CreateResponse.newBuilder().setCreated(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void createNews(CreateNewsRequest request, StreamObserver<CreateResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }
        String heading = request.getHeading();
        Category category = categoryRepo.findByName(request.getCategoryName());
        News news = new News(heading, category);
        CreateResponse response = CreateResponse.newBuilder().setCreated(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createSource(CreateSourceRequest request, StreamObserver<CreateResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            String name = request.getName();
            String reference = request.getReference();
            News news = newsRepo.findById(Long.valueOf(request.getNewsId())).orElse(null);
            CreateResponse response;
            if (news == null) {
                response = CreateResponse.newBuilder().setCreated(false).build();
            } else {
                sourceRepo.save(new com.example.demo.domain.Source(name, reference, news));
                response = CreateResponse.newBuilder().setCreated(true).build();
            }
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void editCategory(EditCategoryRequest request, StreamObserver<EditResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            Category category = categoryRepo.findById(Long.parseLong(request.getId())).orElse(null);
            String name = request.getName();
            EditResponse.Builder responseBuilder = EditResponse.newBuilder();
            if (category != null) {
                category.setName(name);
                categoryRepo.save(category);
                responseBuilder.setEdited(true);
            } else {
                responseBuilder.setEdited(false);
            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void editSource(EditSourceRequest request, StreamObserver<EditResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            com.example.demo.domain.Source source = sourceRepo.findById(Long.parseLong(request.getId())).orElse(null);
            EditResponse.Builder responseBuilder = EditResponse.newBuilder();
            if (source != null) {
                source.setName(request.getName());
                source.setReference(request.getReference());
                sourceRepo.save(source);
                responseBuilder.setEdited(true);
            } else {
                responseBuilder.setEdited(false);
            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

    }

    @Override
    public void editNews(EditNewsRequest request, StreamObserver<EditResponse> responseObserver) {
        if (!authenticationService.authenticateByToken(request.getToken())) {
            responseObserver.onError(new StatusException(Status.UNAUTHENTICATED));
        }else {
            News news = newsRepo.findById(Long.parseLong(request.getId())).orElse(null);
            EditResponse.Builder responseBuilder = EditResponse.newBuilder();
            if (news != null) {
                news.setHeading(request.getHeading());
                news.setCategory(categoryRepo.findByName(request.getCategory()));
                newsRepo.save(news);
                responseBuilder.setEdited(true);
            } else {
                responseBuilder.setEdited(false);
            }
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
}
