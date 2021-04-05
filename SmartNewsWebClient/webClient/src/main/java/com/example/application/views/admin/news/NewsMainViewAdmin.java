package com.example.application.views.admin.news;

import com.example.application.dataProviders.ResourcesDataProvider;
import com.example.application.domain.News;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NewsMainViewAdmin extends VerticalLayout {
    private final ResourcesDataProvider resourcesDataProvider;

    private final NavbarAdmin navbarAdmin;
    private Grid<News> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public NewsMainViewAdmin(ResourcesDataProvider resourcesDataProvider,
                             CreateNewsGridService createNewsGridService){
        this.resourcesDataProvider = resourcesDataProvider;
        //UI initialization
        navbarAdmin = new NavbarAdmin();
        grid = createNewsGridService.createGrid();
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(grid, resourcesDataProvider);
        modificationComponentDisplayed = createNewsComponent;
        grid.addItemClickListener(item->this.itemClickEvent(item.getItem(), createNewsComponent));
        this.displayUIComponents();
    }

    private void itemClickEvent(News news, CreateNewsComponent createNewsComponent){
        EditNewsComponent editNewsComponent = new EditNewsComponent();
        modificationComponentDisplayed = editNewsComponent;
        this.removeAll();
        this.displayUIComponents();
    }

    private void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }


}
