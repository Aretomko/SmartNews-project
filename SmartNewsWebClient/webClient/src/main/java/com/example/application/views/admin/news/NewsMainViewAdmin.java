package com.example.application.views.admin.news;

import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NewsMainViewAdmin extends VerticalLayout {
    private final NewsDataProvider newsDataProvider;

    private final NavbarAdmin navbarAdmin;
    private Grid<News> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public NewsMainViewAdmin(NewsDataProvider newsDataProvider,
                             CreateNewsGridService createNewsGridService){
        this.newsDataProvider = newsDataProvider;
        //UI initialization
        navbarAdmin = new NavbarAdmin();
        grid = createNewsGridService.createGrid();
        CreateNewsComponent createNewsComponent = new CreateNewsComponent(newsDataProvider, grid);
        modificationComponentDisplayed = createNewsComponent;
        grid.addItemClickListener(item->this.itemClickEvent(item.getItem(), createNewsComponent));
        this.displayUIComponents();
    }

    private void itemClickEvent(News news, CreateNewsComponent createNewsComponent){
        EditNewsComponent editNewsComponent = new EditNewsComponent(news,
                newsDataProvider,
                grid,
                this,
                navbarAdmin,
                createNewsComponent);
        modificationComponentDisplayed = editNewsComponent;
        this.removeAll();
        this.displayUIComponents();
    }

    private void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }


}
