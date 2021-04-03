package com.example.application.views.admin.news.sources;

import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class CreateSourcesGridService {
    private final NewsDataProvider newsDataProvider;

    private Grid<Source> grid;

    public CreateSourcesGridService(NewsDataProvider newsDataProvider) {
        this.newsDataProvider = newsDataProvider;
    }

    public Grid<Source> createGrid(News news){
        this.grid = new Grid<>();
        grid.setItems(newsDataProvider.getSourcesByNews(news));
        grid.addColumn(Source::getName).setHeader("Name");
        grid.addColumn(Source::getReference).setHeader("Reference");
        grid.addColumn(Source::getLikes).setHeader("Likes");
        grid.addComponentColumn(this::createDeleteButton).setHeader("Delete");
        return grid;
    }

    private Button createDeleteButton(Source source){
        return new Button("Delete", event->{
           newsDataProvider.deleteSource(source);
        });
    }
}
