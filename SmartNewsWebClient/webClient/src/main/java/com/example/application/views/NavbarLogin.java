package com.example.application.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NavbarLogin extends VerticalLayout {
    public NavbarLogin(){
        Image image = new Image("https://sklquest.com/static/uploads/smartNews.png", "banner");
        image.setHeight("200px");
        this.setPadding(true);
        this.add(image);
        this.setAlignItems(Alignment.CENTER);
        this.getStyle().set("background", "light-blue");
    }
}
