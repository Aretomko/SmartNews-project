import com.example.application.Application;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.Category;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class DataProviderTest {
    @Autowired
    private NewsDataProvider newsDataProvider;

    @Test
    public void getNewsByCategory_CorrectRequest_NewsSetProvided(){
        List<News> news = newsDataProvider.getNewsByCategory("Sport");
        Assert.assertTrue(news.size()>0);
    }

    @Test
    public void getNewsById_CorrectRequest_NewsReceived(){
        News news = newsDataProvider.getNewsById("2");
        Assert.assertTrue(news !=null);
    }

    @Test
    public void getAllNews_CorrectRequest_NewsReceived(){
        List<News> news = newsDataProvider.getAllNews();
        Assert.assertTrue(news.size()>0);
    }
    @Test
    public void getSourcesByNews_CorrectRequest_SourcesReceived(){
        List<Source> sources = newsDataProvider.getSourcesByNews(newsDataProvider.getNewsById("2"));
        Assert.assertTrue(sources.size()>0);
    }
    @Test
    public void getGetAllCategorized_CorrectRequest_CategoriesListReceived(){
        List<Category> categories = newsDataProvider.getAllCategories();
        Assert.assertTrue(categories.size()>0);
    }
    @Test
    public void CreateCategory_CorrectRequest_NewCategoryCreated(){
        boolean created = newsDataProvider.createCategory("New Category");
        Assert.assertTrue(created);
    }
    @Test
    public void CreateSource_CorrectRequest_NwSourceCreated(){
        boolean created = newsDataProvider.createSource("New source", "reference", "2");
        Assert.assertTrue(created);
    }
    @Test
    public void CreateNews_CorrectRequest_NewsCreated(){
        boolean created = newsDataProvider.createNews("heaidng", "Sport");
        Assert.assertTrue(created);
    }
    @Test
    public void EditCategory_CorrectRequest_CategoryEdited(){
         boolean edited = newsDataProvider.editCategory("1", "Sport");
         Assert.assertTrue(edited);
    }
    @Test
    public void EditSource_CorrectRequest_CategoryEdited(){
        boolean edited = newsDataProvider.editSource("4", "editedNme", "reference");
        Assert.assertTrue(edited);
    }
    @Test
    public void EditNews_CorrectRequest_CategoryEdited(){
        boolean edited = newsDataProvider.editNews("2", "heading", "Sport");
        Assert.assertTrue(edited);
    }
}
