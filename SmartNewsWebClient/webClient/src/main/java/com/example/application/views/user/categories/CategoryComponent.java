package com.example.application.views.user.categories;

import com.example.application.domain.Category;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class CategoryComponent extends VerticalLayout {
    private Label nameLabel;
    private Button seeNewsButton;
    public CategoryComponent(Category category){
        nameLabel = new Label(category.getName());
        seeNewsButton = new Button("See news", event->{
            VaadinSession.getCurrent().setAttribute("category", category.getName());
            UI.getCurrent().navigate("news");
        });
        seeNewsButton.getStyle().set("color", "black");
        seeNewsButton.getStyle().set("background-color", "#F1C933");
        this.add(nameLabel, seeNewsButton);
        //style
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.getStyle().set("border-style", "solid");
        this.getStyle().set("border-width", "3px");
        this.getStyle().set("border-color", "#F1C933");
        this.setAlignItems(Alignment.CENTER);
    }
}
