package com.example.application.views.admin.categories;

import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CategoriesMainViewAdmin extends VerticalLayout {
    private final NavbarAdmin navbarAdmin;
    private final NewsDataProvider newsDataProvider;
    private Grid<Category> grid;
    private HorizontalLayout modificationComponentDisplayed;


    public CategoriesMainViewAdmin(NewsDataProvider newsDataProvider,
                                   CreateCategoriesGridService createCategoriesGridService){
        this.newsDataProvider = newsDataProvider;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createCategoriesGridService.createGrid();
        CreateCategoryComponent createCategoryComponent = new CreateCategoryComponent(grid, this.newsDataProvider);
        this.modificationComponentDisplayed = createCategoryComponent;
        grid.addItemClickListener(item -> this.itemClickEvent(item.getItem(), createCategoryComponent));
        this.displayUiComponents();
    }


    private void itemClickEvent(Category category, CreateCategoryComponent createCategoryComponent){
        this.modificationComponentDisplayed = new EditCategoryComponent(category,newsDataProvider, grid, this, navbarAdmin, createCategoryComponent );
        this.removeAll();
        this.displayUiComponents();
    }

    private void displayUiComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
