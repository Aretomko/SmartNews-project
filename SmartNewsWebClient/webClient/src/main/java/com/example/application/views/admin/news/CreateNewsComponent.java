package com.example.application.views.admin.news;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.dataProviders.ResourcesDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Resource;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class CreateNewsComponent extends HorizontalLayout {
    private final NewsDataProvider newsDataProvider;
    private final Grid<News> grid;

    private Label createLabel;
    private TextField heading;
    private Select<String> categorySelect;

    public CreateNewsComponent(NewsDataProvider newsDataProvider,
                               Grid<News> grid){
        this.newsDataProvider = newsDataProvider;
        this.grid = grid;
        //UI initialization
        this
    }
}
