package com.example.application.views.user;

import com.example.application.dataProviders.InternationalizationProvider;
import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.domain.Source;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class NewsComponent extends VerticalLayout {

    //UI components
    private Label heading;

    public NewsComponent(InternationalizationProvider internationalizationProvider,
                         News news,
                         NewsDataProvider newsDataProvider){
        this.setAlignItems(Alignment.CENTER);
        heading=new Label(news.getHeading());
        this.add(heading);
        List<Source> sources = newsDataProvider.getSourcesByNews(news);
        if(sources !=null && sources.size()!=0) {
            for (Source source : sources) {
                this.add(new SourceComponent(internationalizationProvider, source, newsDataProvider));
            }
        }else {
            this.add(new Label("No sources jet"));
        }
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.getStyle().set("border-style", "solid");
        this.getStyle().set("border-width", "3px");
        this.getStyle().set("border-color", "#F1C933");
    }
}
