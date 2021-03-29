package com.example.application.views.admin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainAdminView extends VerticalLayout {
    private final NavbarAdmin navbarAdmin;

    public MainAdminView(){
        navbarAdmin = new NavbarAdmin();
        VerticalLayout centeredContent = new VerticalLayout();
        centeredContent.setWidth("100%");
        centeredContent.setAlignItems(Alignment.CENTER);
        centeredContent.setPadding(true);
        Label label = new Label("Welcome to admin page");
        centeredContent.add(label);
        this.add(navbarAdmin, centeredContent);
    }
}
