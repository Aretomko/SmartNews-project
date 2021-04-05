package com.example.application.views.admin.categories;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.Category;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateCategoryComponent extends HorizontalLayout {
    private final NewsDataProvider newsDataProvider;
    private Grid<Category> grid;

    private Label createLabel;
    private TextField categoryName;
    private Button submitButton;

    public CreateCategoryComponent(Grid<Category> grid,
                                   NewsDataProvider newsDataProvider){
        this.newsDataProvider = newsDataProvider;
        this.grid = grid;

        createLabel = new Label("Create new category");
        categoryName = new TextField("category name");
        submitButton = new Button("Create", event->{

        });

    }
    public void createNewCategory(){
        boolean created = newsDataProvider.create
    }
}
