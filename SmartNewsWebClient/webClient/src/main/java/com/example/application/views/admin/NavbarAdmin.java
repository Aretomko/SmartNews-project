package com.example.application.views.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NavbarAdmin extends VerticalLayout {
    public NavbarAdmin(){
        HorizontalLayout centered = new HorizontalLayout();
        Button categories = new com.vaadin.flow.component.button.Button("Categories" , event -> {
            UI.getCurrent().navigate("admin/categories");
        });
        Button news = new com.vaadin.flow.component.button.Button("News" , event -> {
            UI.getCurrent().navigate("admin/news");
        });
        Button resources = new com.vaadin.flow.component.button.Button("Resources" , event -> {
            UI.getCurrent().navigate("admin/resources");
        });
        this.setWidth("100%");
        this.setMinHeight("100px");
        this.setAlignItems(Alignment.CENTER);
        centered.add();
        this.add(centered);
    }
}
