package com.example.application.views.admin.news;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class CreateNewsComponent extends HorizontalLayout {
    private final NewsDataProvider newsDataProvider;
    private final Grid<News> grid;

    private Label createLabel;
    private TextField heading;
    private Select<String> categorySelect;
    private Button submitButton;

    public CreateNewsComponent(NewsDataProvider newsDataProvider,
                               Grid<News> grid){
        this.newsDataProvider = newsDataProvider;
        this.grid = grid;

        //UI initialization
        createLabel = new Label("Create new news");
        heading = new TextField("heading");
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        submitButton = new Button("Create", event->{

        });
    }
    public void createNews(){
        boolean created = newsDataProvider.createNews(heading.getValue(), categorySelect.getValue());
        if(!created) Notification.show("News was not created, wrong data")
                ;
        heading.setValue("");
        categorySelect.setValue("");
        grid.setItems(newsDataProvider.getAllNews());
    }
}
