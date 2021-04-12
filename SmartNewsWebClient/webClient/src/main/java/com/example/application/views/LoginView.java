package com.example.application.views;

import com.example.application.dataProviders.UserDataProvider;
import com.example.application.service.AuthService;
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
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;


@Route(value = "login")
@PageTitle("Login")
public class LoginView extends Div {
	public static final String ROUTE = "login";

	public LoginView(UserDataProvider userDataProvider,
					 AuthService authService) {
		//block to center the form
		VerticalLayout centered = new VerticalLayout();
		centered.setAlignItems(FlexComponent.Alignment.CENTER);
		centered.setWidth("100%");
		//create navbar
		//form
		VerticalLayout  verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("auto");
		verticalLayout.setPadding(true);
		com.example.application.views.NavbarLogin navbarView = new com.example.application.views.NavbarLogin();
		TextField userNameTextField = new TextField("identifier");
		PasswordField passwordField = new PasswordField("password");
		Button submitButton = new Button("Увійти" , event -> {
			try {
				String token = userDataProvider.authenticate(userNameTextField.getValue(), passwordField.getValue());
				VaadinSession.getCurrent().setAttribute("token", token);
				authService.authenticate(userNameTextField.getValue());
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
		verticalLayout.add(userNameTextField, passwordField, centerSubmitButton);
		centered.add(verticalLayout);
		add(navbarView,centered);
	}
}