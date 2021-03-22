package com.example.application.views.user;

import com.example.application.dataProviders.InternationalizationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.server.VaadinSession;

public class NavbarUser extends VerticalLayout {
    public NavbarUser(InternationalizationProvider internationalizationProvider){
        //get selected language;
        String language = VaadinSession.getCurrent().getAttribute("language").toString();

        HorizontalLayout centered = new HorizontalLayout();
        //todo
//        Button announcementsButton = new com.vaadin.flow.component.button.Button( internalizationProvider.userAppointmentsStrings().get(language), event -> {
//            UI.getCurrent().navigate("appointments");
//        });
//        Button centersButton = new com.vaadin.flow.component.button.Button(internalizationProvider.userBookStrings().get(language) , event -> {
//            UI.getCurrent().navigate("book");
//        });
//        Button groupsButton = new com.vaadin.flow.component.button.Button(internalizationProvider.userCentersStrings().get(language) , event -> {
//            UI.getCurrent().navigate("centers");
//        });
        //style buttons
        Select select = new Select();
        select.setValue("English");
        select.setItems("Polski", "English");
        select.addValueChangeListener(event->{
            VaadinSession.getCurrent().setAttribute("language", select.getValue());
            UI.getCurrent().getPage().reload();
        });
        this.setWidth("100%");
        this.setMinHeight("100px");
        this.setAlignItems(Alignment.CENTER);
        //todo add button to the view
        centered.add();
        this.add(centered);
        this.getStyle().set("background", "#0D49C0");
        this.getStyle().set("margin-top", "0px");
        this.getStyle().set("margin-left", "0px");
        this.getStyle().set("margin-right", "0px");
        this.add(select);
    }
}
