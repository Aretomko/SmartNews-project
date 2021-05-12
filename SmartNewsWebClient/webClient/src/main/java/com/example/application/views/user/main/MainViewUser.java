package com.example.application.views.user.main;

import com.example.application.dataProvider.InternationalizationProvider;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;
import com.example.application.views.user.NavbarUser;
import com.example.application.views.user.NewsComponent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;
@Route("")
public class MainViewUser extends VerticalLayout {
    private NavbarUser navbarUser;
    //UI components
    private Label lastNews;

    public MainViewUser(InternationalizationProvider internationalizationProvider,
                        NewsDataProvider newsDataProvider){
        //get selected language;
        String language;
        try {
            language = VaadinSession.getCurrent().getAttribute("language").toString();
        } catch (Exception ex) {
            language = "Polski";
            VaadinSession.getCurrent().setAttribute("language", "Polski");
        }
        this.navbarUser = new NavbarUser(internationalizationProvider);
        VerticalLayout centered = new VerticalLayout();
        VerticalLayout labelLayout = new VerticalLayout();
        labelLayout.setWidth("100%");
        labelLayout.setAlignItems(Alignment.CENTER);
        lastNews = new Label(internationalizationProvider.getLastButtonStrings().get(language));
        labelLayout.add(lastNews);
        centered.setWidth("80%");
        this.setAlignItems(Alignment.CENTER);
        centered.add(labelLayout);
        List<News> news = newsDataProvider.getNewsByCategory("Last");
        if (news !=null || news.size()!=0) {
            for (News n : news) {
                centered.add(new NewsComponent(internationalizationProvider, n, newsDataProvider));
            }
        }else{
            centered.add(new Label("No news jet"));
        }
        this.add(navbarUser, centered);
    }
}
