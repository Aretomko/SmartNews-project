package views.news;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.news.CreateNewsComponent;
import com.example.application.views.admin.news.CreateNewsGridService;
import com.example.application.views.admin.news.EditNewsComponent;
import com.example.application.views.admin.news.NewsMainViewAdmin;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class EditNewsComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void editNews_ValidRequest_HeadingUpdated() {
        News news = newsDataProvider.getAllNews().get(0);
        EditNewsComponent editNewsComponent = new EditNewsComponent(
                news,
                newsDataProvider,
                new Grid<News>(),
                new NewsMainViewAdmin(
                        newsDataProvider,
                        new CreateNewsGridService(newsDataProvider)
                ),
                new NavbarAdmin(),
                new CreateNewsComponent(newsDataProvider, new Grid<News>())
                );
        String newHeading = "edited heading";
        editNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect= new Select<>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(newsDataProvider.getAllCategories().get(0).getName());
        editNewsComponent.setCategorySelect(categorySelect);
        editNewsComponent.editNews();
        News newsAfterUpdate = newsDataProvider.getAllNews().get(0);
        Assert.assertEquals( newsAfterUpdate.getHeading(),newHeading);
    }
    @Test
    public void editNews_ValidRequest_CategoryUpdated() {
        News news = newsDataProvider.getAllNews().get(0);
        EditNewsComponent editNewsComponent = new EditNewsComponent(
                news,
                newsDataProvider,
                new Grid<News>(),
                new NewsMainViewAdmin(
                        newsDataProvider,
                        new CreateNewsGridService(newsDataProvider)
                ),
                new NavbarAdmin(),
                new CreateNewsComponent(newsDataProvider, new Grid<News>())
        );
        String newHeading = "edited heading";
        editNewsComponent.setHeading(new TextField(newHeading));
        Select<String> categorySelect= new Select<>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        categorySelect.setValue(newsDataProvider.getAllCategories().get(0).getName());
        editNewsComponent.setCategorySelect(categorySelect);
        editNewsComponent.editNews();
        News newsAfterUpdate = newsDataProvider.getAllNews().get(0);
        Assert.assertEquals( newsAfterUpdate.getHeading(),newHeading);
    }


}
