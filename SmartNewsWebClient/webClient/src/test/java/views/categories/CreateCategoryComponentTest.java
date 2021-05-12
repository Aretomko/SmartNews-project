package views.categories;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.views.admin.categories.CreateCategoryComponent;
import com.vaadin.flow.component.grid.Grid;
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
public class CreateCategoryComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void  createNewCategoryTest_CorrectDataProvided_CategoryCreated() {
        CreateCategoryComponent createCategoryComponent = new CreateCategoryComponent(new Grid<Category>(), newsDataProvider);
        int numberOfCategoriesBefore = newsDataProvider.getAllCategories().size();
        createCategoryComponent.setCategoryName(new TextField("New category name"));
        createCategoryComponent.createNewCategory();
        List<Category> categories = newsDataProvider.getAllCategories();
        categories.sort(Comparator.comparing(Category::getId).reversed());
        newsDataProvider.deleteCategory(categories.get(0));
        Assert.assertEquals(categories.size()-1, numberOfCategoriesBefore);
    }
    @Test
    public void  createNewCategoryTest_CorrectDataProvided_CategoryWithProvidedNameCreated() {
        CreateCategoryComponent createCategoryComponent = new CreateCategoryComponent(new Grid<Category>(), newsDataProvider);
        int numberOfCategoriesBefore = newsDataProvider.getAllCategories().size();
        String categoryName = "New category name";
        createCategoryComponent.setCategoryName(new TextField(categoryName));
        createCategoryComponent.createNewCategory();
        List<Category> categories = newsDataProvider.getAllCategories();
        categories.sort(Comparator.comparing(Category::getId).reversed());
        newsDataProvider.deleteCategory(categories.get(0));
        Assert.assertEquals(categories.get(0).getName(), categoryName);
    }
    @Test
    public void  createNewCategoryTest_EmptyNameField_CategoryNotCreated() {
        CreateCategoryComponent createCategoryComponent = new CreateCategoryComponent(new Grid<Category>(), newsDataProvider);
        int numberOfCategoriesBefore = newsDataProvider.getAllCategories().size();
        String categoryName = "New category name";
        createCategoryComponent.setCategoryName(new TextField(categoryName));
        createCategoryComponent.createNewCategory();
        List<Category> categories = newsDataProvider.getAllCategories();
        categories.sort(Comparator.comparing(Category::getId).reversed());
        newsDataProvider.deleteCategory(categories.get(0));
        Assert.assertEquals(categories.size(), numberOfCategoriesBefore);
    }
}
