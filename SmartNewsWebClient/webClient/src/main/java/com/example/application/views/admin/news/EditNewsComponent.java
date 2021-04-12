package com.example.application.views.admin.news;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;


public class EditNewsComponent extends HorizontalLayout {
    private final News news;
    private final NewsDataProvider dataProvider;
    private final Grid<News> grid;
    private final NavbarAdmin navbarAdmin;
    private final CreateNewsComponent createNewsComponent;
    private final NewsMainViewAdmin adminView;
    //UI components
    private Label editLabel;
    private TextField heading;
    private Select<String> categorySelect;
    private Button submitButton;

    public EditNewsComponent(News news,
                             NewsDataProvider dataProvider,
                             Grid<News> grid,
                             NewsMainViewAdmin adminView,
                             NavbarAdmin navbarAdmin,
                             CreateNewsComponent createNewsComponent){
        this.news = news;
        this.adminView = adminView;
        this.dataProvider = dataProvider;
        this.grid = grid;
        this.navbarAdmin = navbarAdmin;
        this.createNewsComponent = createNewsComponent;
        //UI initialization
        editLabel= new Label("Create new news");
        heading = new TextField("heading");
        categorySelect = new Select<>();
        categorySelect.setItems(dataProvider.getCategoriesNames());
        categorySelect.setLabel("category");
        submitButton = new Button("Create", event->{
            this.editNews();
        });
        this.add(editLabel, heading, categorySelect, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void  editNews(){
        if(heading.getValue().equals("") || categorySelect.getValue().equals("") ){
            Notification.show("Fields can not be empty");
        }else{
            boolean edited = dataProvider.editNews(news.getId(), heading.getValue(), categorySelect.getValue());
            if(!edited) Notification.show("Category was not edited, wrong data")
                    ;
            grid.setItems(dataProvider.getAllNews());
            adminView.removeAll();
            adminView.add(navbarAdmin, createNewsComponent, grid);
        }
    }
}
