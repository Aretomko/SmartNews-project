package com.example.application.views.user.news;

import com.example.application.dataProviders.InternationalizationProvider;
import com.example.application.dataProviders.NewsDataProvider;
import com.example.application.domain.News;
import com.example.application.views.user.NavbarUser;
import com.example.application.views.user.NewsComponent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("news")
public class NewsView extends VerticalLayout {
    private Label newsLabel;
    public NewsView(InternationalizationProvider internationalizationProvider,
                    NewsDataProvider newsDataProvider){
        //get selected language;
        String language;
        try {
            language = VaadinSession.getCurrent().getAttribute("language").toString();
        } catch (Exception ex) {
            language = "Polski";
            VaadinSession.getCurrent().setAttribute("language", "Polski");
        }
        String categoryName = (String) VaadinSession.getCurrent().getAttribute("category");
        this.add(new NavbarUser(internationalizationProvider));
        VerticalLayout centered = new VerticalLayout();
        centered.setWidth("80%");
        this.setAlignItems(Alignment.CENTER);
        VerticalLayout labelLayout = new VerticalLayout();
        newsLabel = new Label(internationalizationProvider.getNewsLabelStrings().get(language)+" "+ categoryName);
        labelLayout.setWidth("100%");
        labelLayout.setAlignItems(Alignment.CENTER);
        labelLayout.add(newsLabel);
        centered.add(labelLayout);
        for (News news: newsDataProvider.getNewsByCategory(categoryName)){
            centered.add(new NewsComponent(internationalizationProvider, news, newsDataProvider));
        }
        this.add(centered);
    }
}
