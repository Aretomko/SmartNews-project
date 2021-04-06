package com.example.application.views.admin.news.sources;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class SourcesMainView extends VerticalLayout {
    private final NewsDataProvider newsDataProvider;

    private final NavbarAdmin navbarAdmin;
    private Grid<Source> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public SourcesMainView(NewsDataProvider newsDataProvider,
                           CreateSourcesGridService createSourcesGridService){
        this.newsDataProvider = newsDataProvider;
        //UI initialization
        navbarAdmin = new NavbarAdmin();
        String newsId = (String) VaadinSession.getCurrent().getAttribute("newsId");
        News news = newsDataProvider.getNewsById(newsId);
        grid = createSourcesGridService.createGrid(news);
        CreateSourceComponent createSourceComponent = new CreateSourceComponent(newsDataProvider, grid, news);
        this.modificationComponentDisplayed = createSourceComponent;
        grid.addItemClickListener(item->this.itemClickEvent(item.getItem(), createSourceComponent));
        this.displayUIComponents();
    }

    private void itemClickEvent(Source source, CreateSourceComponent createSourceComponent){
        this.modificationComponentDisplayed = new EditSourceComponent(source, newsDataProvider, grid, this, navbarAdmin, createSourceComponent);
        this.removeAll();
        this.displayUIComponents();
    }

    private void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
