package com.example.application.views.admin.news;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNewsGridService  {
    private final NewsDataProvider newsDataProvider;

    private Grid<News> grid;

    public CreateNewsGridService(NewsDataProvider newsDataProvider) {
        this.newsDataProvider = newsDataProvider;
    }

    public Grid<News> createGrid(){
        this.grid = new Grid<News>();
        grid.setItems(newsDataProvider.getAllNews());
        grid.addColumn(News::getHeading).setHeader("Heading");
        grid.addComponentColumn(item-> this.createManageSourcesButton()).setHeader("Manage sources");
        grid.addComponentColumn(this::createDeleteButton).setHeader("Delete");
        return grid;
    }

    private Button createManageSourcesButton(){
        return new Button("Manage sources", event->{
            UI.getCurrent().navigate("admin/news/sources");
        });
    }

    private Button createDeleteButton(News news){
        return new Button("Delete", event->{
            newsDataProvider.deleteNewsWithSources(news);
        });
    }
}
