package com.example.application.views.user.registration;

import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.GroupService;
import com.example.application.service.UserService;
import com.example.application.views.NavbarLogin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class RegistrationMainPage extends VerticalLayout {
    public RegistrationMainPage(UserService userService,
                                GroupService groupService){
        VerticalLayout centered = new VerticalLayout();
        centered.setAlignItems(FlexComponent.Alignment.CENTER);
        centered.setWidth("100%");
        //create navbar
        //form
        VerticalLayout  verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("auto");
        verticalLayout.setPadding(true);
        NavbarLogin navbarView = new NavbarLogin();
        TextField userName= new TextField("Name");
        TextField userSurname= new TextField("Name");
        TextField userId= new TextField("IdentificationCode");
        EmailField emailField = new EmailField("Email");
        NumberField numberField = new NumberField("Age");
        numberField.setMin(0);
        numberField.setMax(100);
        Button submitButton = new Button("Register" , event -> {
            try {
                User user = new User(userName.getValue(), userSurname.getValue(), emailField.getValue());
                userService.save(user);
                List<Team> groups = groupService.getAllGroups();
                for(Team group:groups){
                    if (numberField.getValue()<group.getMaxAge()&&numberField.getValue()>group.getMinAge()){
                        user.setTeam(group);
                    }
                }

            } catch (Exception e) {
                Notification.show("Wrong data provided");
            }
            UI.getCurrent().navigate("login");
        });
        submitButton.getStyle().set("color", "black");
        submitButton.getStyle().set("background-color", "#F1C933");
        VerticalLayout centerSubmitButton = new VerticalLayout();
        centerSubmitButton.setAlignItems(FlexComponent.Alignment.CENTER);
        centerSubmitButton.add(submitButton);
        verticalLayout.add(userName, userSurname, emailField, userId, numberField, centerSubmitButton);
        centered.add(verticalLayout);
        add(navbarView,centered);
    }
}
