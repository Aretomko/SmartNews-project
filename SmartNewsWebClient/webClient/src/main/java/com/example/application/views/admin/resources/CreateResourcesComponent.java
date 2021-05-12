package com.example.application.views.admin.resources;

import com.example.application.dataProvider.ResourcesDataProvider;
import com.example.application.domain.domain.Resource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateResourcesComponent extends HorizontalLayout {
    private final ResourcesDataProvider resourcesDataProvider;
    private final Grid<Resource> grid;

    private Label createLabel;
    private TextField nameField;
    private TextField referenceField;
    private Button submitButton;

    public CreateResourcesComponent(ResourcesDataProvider resourcesDataProvider,
                                    Grid<Resource> grid){
        this.resourcesDataProvider = resourcesDataProvider;
        this.grid = grid;
        //UI initialization
        createLabel = new Label("Create new reference");
        createLabel.setHeight("wrap-content");
        createLabel.getStyle().set("font","400 13.3333px Arial");
        createLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        createLabel.getStyle().set("font-weight", "500");

        nameField = new TextField("resource name");
        referenceField = new TextField("reference");

        submitButton = new Button("Create", event->{
            this.createResource();
        });
        this.add(createLabel , nameField, referenceField, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void createResource(){
        boolean created = resourcesDataProvider.createResource(nameField.getValue(), referenceField.getValue());
        if(!created) Notification.show("Wrong data, resource was not created");
        nameField.setValue("");
        referenceField.setValue("");
        grid.setItems(resourcesDataProvider.getAllResources());
    }
}
