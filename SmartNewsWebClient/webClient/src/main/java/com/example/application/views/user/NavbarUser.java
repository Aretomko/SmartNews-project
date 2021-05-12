package com.example.application.views.user;

import com.example.application.dataProvider.InternationalizationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.server.VaadinSession;

public class NavbarUser extends HorizontalLayout {
    public NavbarUser(InternationalizationProvider internationalizationProvider) {
        //get selected language;
        String language;
        try {
            language = VaadinSession.getCurrent().getAttribute("language").toString();
        } catch (Exception ex) {
            language = "Polski";
            VaadinSession.getCurrent().setAttribute("language", "Polski");
        }
        HorizontalLayout loginLayout = new HorizontalLayout();
        loginLayout.setWidth("20%");
        Image logo = new Image("http://sklquest.com/static/uploads/newsLogo.png", "logo");
        logo.setHeight("70px");
        logo.setWidth("110px");
        logo.getStyle().set("margin", "20px");
        loginLayout.add(logo);

        HorizontalLayout space = new HorizontalLayout();
        space.setWidth("65%");

        HorizontalLayout centered = new HorizontalLayout();

        Button categoriesButton = new com.vaadin.flow.component.button.Button(internationalizationProvider.getCategoriesButtonString().get(language), event -> {
            UI.getCurrent().navigate("categories");
        });
        Button lestNewsButton = new com.vaadin.flow.component.button.Button(internationalizationProvider.getLastButtonStrings().get(language), event -> {
            UI.getCurrent().navigate("last");
        });

        //style buttons
        categoriesButton.getStyle().set("color", "black");
        categoriesButton.getStyle().set("background-color", "#F1C933");
        lestNewsButton.getStyle().set("color", "black");
        lestNewsButton.getStyle().set("background-color", "#F1C933");

        Select select = new Select();
        select.setValue("English");
        select.setItems("Polski", "English");
        select.addValueChangeListener(event -> {
            VaadinSession.getCurrent().setAttribute("language", select.getValue());
            UI.getCurrent().getPage().reload();
        });

        this.setWidth("100%");
        this.setMinHeight("100px");
        this.add(centered);
        this.getStyle().set("background", "#0D49C0");
        centered.add(categoriesButton, lestNewsButton, select);
        centered.getStyle().set("padding-top", "30px");
        this.add(loginLayout, space , centered);
    }
}
