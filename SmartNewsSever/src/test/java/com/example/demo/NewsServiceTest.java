package com.example.demo;

import com.example.demo.domain.Category;
import com.example.demo.domain.News;
import com.example.demo.domain.Source;
import com.example.demo.repos.CategoryRepo;
import com.example.demo.repos.NewsRepo;
import com.example.demo.repos.SourceRepo;
import com.thinhda.spring.grpc.core.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusException;
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
public class NewsServiceTest {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private SourceRepo sourceRepo;

    @Test
    public void getNewsByCategory(){
        GetNewsByCategoryRequest request = GetNewsByCategoryRequest.newBuilder()
                .setToken("token")
                .setCategory("Sport")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleNewsResponse response = stub.getNewsByCategory(request);
        Assert.assertTrue(response.getNewsCount()==categoryRepo.findByName("Sport").getNews().size());
    }
    @Test(expected = StatusRuntimeException.class)
    public void getNewsByCategory_CategoryNameDoNotExist(){
        GetNewsByCategoryRequest request = GetNewsByCategoryRequest.newBuilder()
                .setToken("token")
                .setCategory("Sportttt")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleNewsResponse response = stub.getNewsByCategory(request);
    }
    @Test(expected = StatusRuntimeException.class)
    public void getNewsByCategory_ExistedToken_ExceptionReturned(){
        GetNewsByCategoryRequest request = GetNewsByCategoryRequest.newBuilder()
                .setToken("t")
                .setCategory("Sport")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleNewsResponse response = stub.getNewsByCategory(request);
    }
    @Test
    public void getNewsById(){
        GetNewsByIdRequest request = GetNewsByIdRequest.newBuilder()
                .setToken("token")
                .setId("2")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        SingleNewsResponse response = stub.getNewsById(request);
        String responseHeading = response.getNews().getHeading();
        String headingInDb = newsRepo.findById(Long.parseLong("2")).get().getHeading();
        Assert.assertEquals(responseHeading, headingInDb);
    }
    @Test(expected = StatusRuntimeException.class)
    public void getNewsById_IncorrectToken_ExceptionReceived(){
        GetNewsByIdRequest request = GetNewsByIdRequest.newBuilder()
                .setToken("tokecsdcdsfsdfsdn")
                .setId("2")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        SingleNewsResponse response = stub.getNewsById(request);
    }
    @Test(expected = StatusRuntimeException.class)
    public void getNewsById_IdDoNotExist_ExceptionReceived(){
        GetNewsByIdRequest request = GetNewsByIdRequest.newBuilder()
                .setToken("token")
                .setId("1000000")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        SingleNewsResponse response = stub.getNewsById(request);
    }
    @Test
    public void getAllNewsTest(){
        GetAllNewsRequest request = GetAllNewsRequest.newBuilder()
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleNewsResponse response = stub.getAllNews(request);
        Assert.assertEquals(response.getNewsCount(), newsRepo.findAll().size());
    }
    @Test(expected = StatusRuntimeException.class)
    public void getAllNews_WrongToken_ExceptionReceived(){
        GetAllNewsRequest request = GetAllNewsRequest.newBuilder()
                .setToken("token34234234")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleNewsResponse response = stub.getAllNews(request);
    }
    @Test()
    public void fetAllCategories(){
        GetCategoriesRequest request = GetCategoriesRequest.newBuilder()
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleCategoriesResponse response = stub.getAllCategories(request);
        Assert.assertEquals(response.getCategoryCount(), categoryRepo.findAll().size());
    }
    @Test(expected = StatusRuntimeException.class)
    public void getAllCategories_WrongUserToken_exceptionReceived(){
        GetCategoriesRequest request = GetCategoriesRequest.newBuilder()
                .setToken("token34234234234")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleCategoriesResponse response = stub.getAllCategories(request);
    }
    @Test
    public void getSourcesByNews(){
        GetSourcesByNewsRequest request = GetSourcesByNewsRequest.newBuilder()
                .setToken("token")
                .setNewsId("2")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleSourcesResponse response = stub.getSourcesByNews(request);
        Assert.assertEquals(response.getSourceCount(), newsRepo.findById(Long.parseLong("2")).get().getSources().size());
    }
    @Test(expected = StatusRuntimeException.class)
    public void getSourcesById_IdDoNotExist_ExceptionReceived(){
        GetSourcesByNewsRequest request = GetSourcesByNewsRequest.newBuilder()
                .setToken("token")
                .setNewsId("22342342323")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleSourcesResponse response = stub.getSourcesByNews(request);
    }
    @Test(expected = StatusRuntimeException.class)
    public void getSourcesById_WrongToken_ExceptionReceived(){
        GetSourcesByNewsRequest request = GetSourcesByNewsRequest.newBuilder()
                .setToken("token343434r2333434")
                .setNewsId("2")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        MultipleSourcesResponse response = stub.getSourcesByNews(request);
    }
    @Test
    public void deleteCategory(){
        String categoryName = "New category";
        categoryRepo.save(new Category(categoryName));
        String id = categoryRepo.findByName(categoryName).getId().toString();
        DeleteCategoryRequest request = DeleteCategoryRequest.newBuilder()
                .setCategoryId(id)
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        DeleteResponse response = stub.deleteCategory(request);
        Assert.assertEquals(null, categoryRepo.findByName(categoryName));
    }
    @Test(expected = StatusRuntimeException.class)
    public void deleteCategory_IdDoNotExist_ExceptionRetrned(){
        DeleteCategoryRequest request = DeleteCategoryRequest.newBuilder()
                .setCategoryId("1231234231431")
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        DeleteResponse response = stub.deleteCategory(request);
    }
    @Test
    public void deleteSource(){
        String sourceName = "Source name";
        String reference ="Reference";
        News news = newsRepo.findById(2l).get();
        Source source = new Source(sourceName, reference, news);
        source.setId(1000000000L);
        sourceRepo.save(source);
        DeleteSourceRequest request = DeleteSourceRequest.newBuilder()
                .setSourceId("1000000000")
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        DeleteResponse response = stub.deleteSource(request);
        Assert.assertTrue(!newsRepo.findById(2l).get().getSources().contains(source));
    }
    @Test
    public void deleteNews(){
        String heading = "heading";
        Category category = categoryRepo.findById(1l).get();
        News news = new News(heading, category);
        news.setId(1000000000l);
        newsRepo.save(news);
        DeleteNewsRequest request =DeleteNewsRequest.newBuilder()
                .setNewsId("1000000000")
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        DeleteResponse response = stub.deleteNews(request);
        Assert.assertTrue(!newsRepo.findAll().contains(category));
    }
    @Test
    public void CreateCategory(){
        int numberOfCategoriesBefore = categoryRepo.findAll().size();
        CreateCategoryRequest request = CreateCategoryRequest.newBuilder()
                .setName("name")
                .setToken("token")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.createCategory(request);
        int numberOfCategoriesAfter = categoryRepo.findAll().size();
        Assert.assertEquals(numberOfCategoriesBefore+1, numberOfCategoriesAfter);
    }
    @Test
    public void createSource(){
        int numberOfSourcesBefore = sourceRepo.findAll().size();
        CreateSourceRequest request = CreateSourceRequest.newBuilder()
                .setReference("reference")
                .setToken("token")
                .setName("name")
                .setNewsId("2")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.createSource(request);
        int numberOfSourcesAfter = sourceRepo.findAll().size();
        Assert.assertEquals(numberOfSourcesAfter, numberOfSourcesBefore+1);
    }
    @Test
    public void editSource(){
        EditSourceRequest request = EditSourceRequest.newBuilder()
                .setToken("token")
                .setReference("new reference")
                .setName("newName")
                .setId("4")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        EditResponse response = stub.editSource(request);
        Source source  = sourceRepo.findById(4l).get();
        Assert.assertEquals(source.getName(), "newName");
        Assert.assertEquals(source.getReference(), "new reference");
    }
    @Test
    public void editCategory(){
        EditCategoryRequest request = EditCategoryRequest.newBuilder()
                .setName("new name")
                .setToken("token")
                .setId("1")
                .build();
        ManagedChannel channel;
        NewsServiceGrpc.NewsServiceBlockingStub stub;
        channel = ManagedChannelBuilder.forTarget("localhost:6565").usePlaintext().build();
        stub = NewsServiceGrpc.newBlockingStub(channel);
        EditResponse response = stub.editCategory(request);
        Category category = categoryRepo.findById(1l).get();
        Assert.assertEquals(category.getName(), "new name");
    }
}
