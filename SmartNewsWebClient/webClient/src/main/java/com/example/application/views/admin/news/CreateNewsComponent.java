package com.example.application.views.admin.news;

import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;
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
        categorySelect = new Select<>();
        categorySelect.setItems(newsDataProvider.getCategoriesNames());
        submitButton = new Button("Create", event->{
            this.createNews();
        });
        this.add(createLabel , heading, categorySelect, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void createNews(){
        if(this.heading.getValue().equals("")||this.categorySelect.getValue().equals("")){
            Notification.show("News was not created, wrong data");
            return;
        }
        boolean created = newsDataProvider.createNews(heading.getValue(), categorySelect.getValue());
        if(!created) Notification.show("News was not created, wrong data")
                ;
        heading.setValue("");
        categorySelect.setValue("");
        grid.setItems(newsDataProvider.getAllNews());
    }

    public void setHeading(TextField heading) {
        this.heading = heading;
    }

    public void setCategorySelect(Select<String> categorySelect) {
        this.categorySelect = categorySelect;
    }
}
