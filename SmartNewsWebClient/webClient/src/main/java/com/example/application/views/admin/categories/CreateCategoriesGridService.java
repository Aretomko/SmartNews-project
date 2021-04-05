package com.example.application.views.admin.categories;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.Category;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoriesGridService {
    private final NewsDataProvider newsDataProvider;

    private Grid<Category> grid;

    public CreateCategoriesGridService(NewsDataProvider newsDataProvider) {
        this.newsDataProvider = newsDataProvider;
    }

    public Grid<Category> createGrid(){
        grid = new Grid<>();
        grid.setItems(newsDataProvider.getAllCategories());
        grid.addColumn(Category::getName).setHeader("Name");
        grid.addColumn(Category::getId).setHeader("Id");
        grid.addComponentColumn(this::createDeleteButton).setHeader("Delete");
        return grid;
    }

    private Button createDeleteButton(Category category){
        return new Button("Delete", event->{
            newsDataProvider.deleteCategory(category);
        });
    }
}
