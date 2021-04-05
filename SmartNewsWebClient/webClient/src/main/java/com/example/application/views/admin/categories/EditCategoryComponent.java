package com.example.application.views.admin.categories;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.Category;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditCategoryComponent extends HorizontalLayout {
    private final Category category;
    private final NewsDataProvider newsDataProvider;
    private final Grid<Category> grid;
    private final CategoriesMainViewAdmin adminView;
    private final NavbarAdmin navbarAdmin;
    private final CreateCategoryComponent createCategoryComponent;
    //UI elements
    private Label editLabel;
    private TextField categoryName;
    private Button submitButton;

    public EditCategoryComponent(Category category,
                                 NewsDataProvider newsDataProvider,
                                 Grid<Category> grid,
                                 CategoriesMainViewAdmin categoriesMainViewAdmin,
                                 NavbarAdmin navbarAdmin,
                                 CreateCategoryComponent createCategoryComponent){
        this.category = category;
        this.newsDataProvider = newsDataProvider;
        this.grid = grid;
        this.adminView = categoriesMainViewAdmin;
        this.navbarAdmin = navbarAdmin;
        this.createCategoryComponent = createCategoryComponent;
        //UI initialization
        editLabel = new Label("Edit category");
        categoryName = new TextField("category name");
        submitButton = new Button("Edit", event->{
            this.editCategory();
        });
        this.add(editLabel,categoryName, submitButton, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void editCategory(){
        if (categoryName.getValue().equals("")) {
            Notification.show("Category name can not be empty");
        }else {
            boolean edited = newsDataProvider.editCategory(category.getId(), categoryName.getValue());
            if (!edited) Notification.show("Category was not edited, wrong data")
                    ;
            grid.getDataProvider().refreshItem(category);
            adminView.removeAll();
            adminView.add(navbarAdmin, createCategoryComponent, grid);
        }
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getCategoryName() {
        return categoryName;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
