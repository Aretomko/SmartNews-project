package views.categories;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.categories.CategoriesMainViewAdmin;
import com.example.application.views.admin.categories.CreateCategoriesGridService;
import com.example.application.views.admin.categories.CreateCategoryComponent;
import com.example.application.views.admin.categories.EditCategoryComponent;
import com.vaadin.flow.component.grid.Grid;
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
public class EditCategoryComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;

    @Test
    private void editCategory(){
        Category category = newsDataProvider.getAllCategories().get(0);
        EditCategoryComponent editCategoryComponent = new EditCategoryComponent(
                category,
                newsDataProvider,
                new Grid<Category>(),
                new CategoriesMainViewAdmin(newsDataProvider, new CreateCategoriesGridService(newsDataProvider)),
                new NavbarAdmin(),
                new CreateCategoryComponent(new Grid<Category>(), newsDataProvider)
        );
        String name= "Edited Name";

        editCategoryComponent.setCategoryName(new TextField(name));

        editCategoryComponent.editCategory();

        Category editedCategory = newsDataProvider.getAllCategories().get(0);

        Assert.assertEquals(name , editedCategory.getName());
    }
    @Test
    private void editCategory_NameNotProvided_NameDoNotChange(){
        Category category = newsDataProvider.getAllCategories().get(0);
        EditCategoryComponent editCategoryComponent = new EditCategoryComponent(
                category,
                newsDataProvider,
                new Grid<Category>(),
                new CategoriesMainViewAdmin(newsDataProvider, new CreateCategoriesGridService(newsDataProvider)),
                new NavbarAdmin(),
                new CreateCategoryComponent(new Grid<Category>(), newsDataProvider)
        );
        String name= "Edited Name";

        editCategoryComponent.setCategoryName(new TextField(name));

        editCategoryComponent.editCategory();

        Category editedCategory = newsDataProvider.getAllCategories().get(0);

        Assert.assertEquals(category.getName() , editedCategory.getName());
    }

}
