package com.example.application.views;

import com.example.application.views.user.registration.RegistrationMainPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteConfiguration;
import com.example.application.dataProviders.LoginProvider;

@Route(value = "login")
@RouteAlias(value = "")
@PageTitle("Login")
public class LoginView extends Div {
	public static final String ROUTE = "login";

	public LoginView() {
		//block to center the form
		VerticalLayout centered = new VerticalLayout();
		centered.setAlignItems(FlexComponent.Alignment.CENTER);
		centered.setWidth("100%");
		//create navbar
		//form
		VerticalLayout  verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("auto");
		verticalLayout.setPadding(true);
		NavbarLogin navbarView = new NavbarLogin();
		TextField userNameTextField = new TextField("identifier");
		PasswordField passwordField = new PasswordField("password");
		Button submitButton = new Button("Увійти" , event -> {
			try {
				(userNameTextField.getValue(), passwordField.getValue());
			} catch (Exception e) {
				Notification.show("Wrong id or password");
			}
			UI.getCurrent().navigate("personal");
		});
		submitButton.getStyle().set("color", "black");
		submitButton.getStyle().set("background-color", "#F1C933");
		VerticalLayout centerSubmitButton = new VerticalLayout();
		centerSubmitButton.setAlignItems(FlexComponent.Alignment.CENTER);
		centerSubmitButton.add(submitButton);
		VerticalLayout centerRegistrationButton = new VerticalLayout();
		centerRegistrationButton.setAlignItems(FlexComponent.Alignment.CENTER);
		centerRegistrationButton.add(new Button("Registration", event->{
			RouteConfiguration.forSessionScope().setRoute("registration", RegistrationMainPage.class);
			UI.getCurrent().navigate("registration");
		}));
		verticalLayout.add(userNameTextField, passwordField, centerSubmitButton, centerRegistrationButton);
		centered.add(verticalLayout);
		add(navbarView,centered);
	}
}