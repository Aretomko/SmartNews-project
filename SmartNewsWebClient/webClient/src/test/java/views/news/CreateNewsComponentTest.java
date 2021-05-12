package views.news;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.domain.domain.News;
import com.example.application.views.admin.news.CreateNewsComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class CreateNewsComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void createNewsComponent_ValidData_NewsObjectCreated(){
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(
                newsDataProvider,
                new Grid<News>()
        );
        int newsCountBeforeCreation = newsDataProvider.getAllCategories().size();
        String newHeading= "edited";
        createNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect = new Select<String>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(newsDataProvider.getAllCategories().get(0).getName());
        createNewsComponent.setCategorySelect(categorySelect);
        createNewsComponent.createNews();
        Assert.assertEquals(newsCountBeforeCreation+1, newsDataProvider.getAllCategories().size());
    }
    @Test
    public void createNewsComponent_ValidData_NewsWithRightHeadingCreated(){
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(
                newsDataProvider,
                new Grid<News>()
        );
        int newsCountBeforeCreation = newsDataProvider.getAllCategories().size();
        String newHeading= "edited";
        createNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect = new Select<String>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(newsDataProvider.getAllCategories().get(0).getName());
        createNewsComponent.setCategorySelect(categorySelect);
        createNewsComponent.createNews();
        List<News> news = newsDataProvider.getAllNews();
        news.sort(Comparator.comparing(News::getId).reversed());
        //getting lastly created object
        Assert.assertEquals(news.get(0).getHeading(), newHeading);
    }
    @Test
    public void createNewsComponent_ValidData_NewsWithRightCategoryCreated(){
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(
                newsDataProvider,
                new Grid<News>()
        );
        int newsCountBeforeCreation = newsDataProvider.getAllCategories().size();
        String newHeading= "edited";
        createNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect = new Select<String>();
        Category category = newsDataProvider.getAllCategories().get(0);
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(category.getName());
        createNewsComponent.setCategorySelect(categorySelect);
        createNewsComponent.createNews();
        List<News> news = newsDataProvider.getAllNews();
        news.sort(Comparator.comparing(News::getId).reversed());
        //getting lastly created object
        Assert.assertEquals(news.get(0).getCategory().getName(), category.getName());
    }
    @Test
    public void createNewsComponent_EmptyHeading_NewsShouldNotBeCreated(){
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(
                newsDataProvider,
                new Grid<News>()
        );
        int newsCountBeforeCreation = newsDataProvider.getAllCategories().size();
        String newHeading= "";
        createNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect = new Select<String>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(newsDataProvider.getAllCategories().get(0).getName());
        createNewsComponent.setCategorySelect(categorySelect);
        createNewsComponent.createNews();
        Assert.assertEquals(newsCountBeforeCreation, newsDataProvider.getAllCategories().size());
    }

}
