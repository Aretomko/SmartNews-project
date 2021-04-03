package com.example.application.dataProviders;

import com.example.application.domain.Category;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsDataProvider {
    ManagedChannel channel;
    NewsServiceGrpc.NewsServiceBlockingStub stub;

    public NewsDataProvider() {
        channel = ManagedChannelBuilder.forTarget("localhost:6790").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
    }

    public List<News> getNewsByCategory(String id){
        GetNewsByCategoryRequest request = GetNewsByCategoryRequest.newBuilder()
                .setCategory(id)
                .setToken("token").build();

        MultipleNewsResponse response  = stub.getNewsByCategory(request);

        List<News> news = new ArrayList<>();
        List<Source> sources = new ArrayList<>();
        for(int i=0; i< response.getNewsCount(); i++){
            for(int j=0; j<response.getNews(i).getSourcesCount();j++){
                sources.add(new Source(
                        response.getNews(i).getSources(j).getId(),
                        response.getNews(i).getSources(j).getName(),
                        response.getNews(i).getSources(j).getReference(),
                        response.getNews(i).getSources(j).getLikes()));
            }
            Category category = new Category(response.getNews(i).getCategory().getId(),
                    response.getNews(i).getCategory().getName());

            news.add(new News(response.getNews(i).getId(),response.getNews(i).getHeading(), sources, category));
            sources.clear();
        }
        return news;
    }
    public News getNewsById(String id){
        GetNewsByIdRequest request = GetNewsByIdRequest.newBuilder().setId(id).setToken("token").build();

        SingleNewsResponse response = stub.getNewsById(request);


        List<Source> sources = new ArrayList<>();
        for(int i=0;i<response.getNews().getSourcesCount();i++){
            sources.add(new Source(
                    response.getNews().getSources(i).getId(),
                    response.getNews().getSources(i).getName(),
                    response.getNews().getSources(i).getReference(),
                    response.getNews().getSources(i).getLikes()));
        }

        Category category = new Category(response.getNews().getCategory().getId(),
                response.getNews().getCategory().getName());

        News news = new News(response.getNews().getId(),
                response.getNews().getHeading(),
                sources,
                category);
        return news;
    }

    public List<News> getAllNews(){
        GetAllNewsRequest request = GetAllNewsRequest.newBuilder().setToken("token").build();

        MultipleNewsResponse response = stub.getAllNews(request);

        List<News> news = new ArrayList<>();
        List<Source> sources = new ArrayList<>();


        for(int i=0; i< response.getNewsCount(); i++){
            for(int j=0; j<response.getNews(i).getSourcesCount();j++){
                sources.add(new Source(
                        response.getNews(i).getSources(j).getId(),
                        response.getNews(i).getSources(j).getName(),
                        response.getNews(i).getSources(j).getReference(),
                        response.getNews(i).getSources(j).getLikes()));
            }
            Category category = new Category(response.getNews(i).getCategory().getId(),
                    response.getNews(i).getCategory().getName());
            news.add(new News(response.getNews(i).getId(),response.getNews(i).getHeading(), sources, category));
            sources.clear();
        }
        return news;
    }

    public void deleteNewsWithSources(News news){
        DeleteNewsRequest request = DeleteNewsRequest.newBuilder().setNewsId(news.getId()).build();

        DeleteResponse response = stub.deleteNews(request);
    }

    public List<Source> getSourcesByNews(News news){
        De
    }

    public void deleteSource(Source source){
        DeleteSourceRequest request = DeleteSourceRequest.newBuilder()
                .setToken("token")
                .setSourceId(source.getId())
                .build();

        DeleteResponse deleteResponse = stub.deleteSource(request);

    }
    public List<Category> getAllCategories(){
        GetCategoriesRequest request = GetCategoriesRequest.newBuilder().setToken("token").build();

        MultipleCategoriesResponse response = stub.getAllCategories(request);

        List<Category> categories = new ArrayList<>();

        for(int i=0; i<response.getCategoryCount();i++){
            categories.add(new Category(
                    response.getCategory(i).getId(),
                    response.getCategory(i).getName()));
        }
        return categories;

    }
    public void deleteCategory(Category category){
        DeleteCategoryRequest request = DeleteCategoryRequest.newBuilder()
                .setCategoryId(category.getId())
                .setToken("token")
                .build();
        DeleteResponse deleteResponse = stub.deleteCategory(request);
    }
}
