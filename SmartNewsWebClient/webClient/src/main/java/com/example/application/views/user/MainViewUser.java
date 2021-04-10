package com.example.application.views.user;

import com.example.application.dataProviders.InternationalizationProvider;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainViewUser extends VerticalLayout {
    private final NavbarUser navbarUser;
    public MainViewUser(InternationalizationProvider internationalizationProvider){
        this.navbarUser = new NavbarUser(internationalizationProvider);
        
    }
}
