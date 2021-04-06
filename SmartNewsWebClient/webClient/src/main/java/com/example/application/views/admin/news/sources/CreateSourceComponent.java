package com.example.application.views.admin.news.sources;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateSourceComponent extends HorizontalLayout {
    private final NewsDataProvider newsDataProvider;
    private final Grid<Source> grid;
    private final News news;
    //UI components
    private Label createLabel;
    private TextField nameField;
    private TextField referenceField;
    private Button createButton;

    public CreateSourceComponent(NewsDataProvider newsDataProvider, Grid<Source> source, News news){
        this.newsDataProvider = newsDataProvider;
        this.grid = source;
        this.news = news;
        //UI initialization
        this.createLabel = new Label("Add new source");
        this.nameField = new TextField("name of the source");
        this.referenceField = new TextField("reference");
        this.createButton = new Button("Create", event->{
            this.addSource();
        });
        this.add(createLabel , nameField, referenceField, createButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void addSource(){
        boolean created = newsDataProvider.createSource(nameField.getValue(), referenceField.getValue(), news.getId());
        if(!created) Notification.show("Error!!")
                ;
        nameField.setValue("");
        referenceField.setValue("");
        grid.setItems(newsDataProvider.getSourcesByNews(news));
    }
}
