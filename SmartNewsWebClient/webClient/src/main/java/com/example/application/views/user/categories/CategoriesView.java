package com.example.application.views.user.categories;

import com.example.application.dataProvider.InternationalizationProvider;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Category;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.user.NavbarUser;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

@Route("categories")
public class CategoriesView extends VerticalLayout {
    private Label categoryLabel;
    public CategoriesView(InternationalizationProvider internationalizationProvider,
                          NewsDataProvider newsDataProvider){
        //get selected language;
        String language;
        try {
            language = VaadinSession.getCurrent().getAttribute("language").toString();
        } catch (Exception ex) {
            language = "Polski";
            VaadinSession.getCurrent().setAttribute("language", "Polski");
        }
        this.add(new NavbarUser(internationalizationProvider));
        VerticalLayout centered = new VerticalLayout();
        centered.setWidth("80%");
        this.add(centered);
        this.setAlignItems(Alignment.CENTER);
        VerticalLayout labelLayout = new VerticalLayout();
        labelLayout.setAlignItems(Alignment.CENTER);
        categoryLabel = new Label(internationalizationProvider.getCategoriesButtonString().get(language));
        labelLayout.add(categoryLabel);
        centered.add(labelLayout);
        this.add(centered);
        List<Category> categories = newsDataProvider.getAllCategories();
        if(categories.size()==0){
            this.add(new Label("No categories jet"));
        }else{
            for(Category category : categories){
                centered.add(new CategoryComponent(category));
            }
        }
    }
}
