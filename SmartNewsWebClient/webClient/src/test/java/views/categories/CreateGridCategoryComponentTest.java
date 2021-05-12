package views.categories;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.views.admin.categories.CreateCategoriesGridService;
import com.vaadin.flow.component.grid.Grid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class CreateGridCategoryComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void createGrid_GridWithAllCategoriesCreated(){
        CreateCategoriesGridService createCategoriesGridService =
                new CreateCategoriesGridService(newsDataProvider);
        Grid<Category> grid  = createCategoriesGridService.createGrid();
    }

}
