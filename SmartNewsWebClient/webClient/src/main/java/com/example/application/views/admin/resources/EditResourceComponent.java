package com.example.application.views.admin.resources;

import com.example.application.dataProvider.ResourcesDataProvider;
import com.example.application.domain.domain.Resource;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditResourceComponent extends HorizontalLayout {
    private final Resource resource;
    private final ResourcesDataProvider resourcesDataProvider;
    private final Grid<Resource> grid;
    private final ResourcesMainViewAdmin adminView;
    private final NavbarAdmin navbarAdmin;
    private final CreateResourcesComponent createResourcesComponent;
    //UI elements
    private Label editLabel;
    private TextField nameField;
    private TextField referenceField;
    private Button submitButton;

    public EditResourceComponent(Resource resource,
                                 ResourcesDataProvider resourcesDataProvider,
                                 Grid<Resource> grid,
                                 ResourcesMainViewAdmin adminView,
                                 NavbarAdmin navbarAdmin,
                                 CreateResourcesComponent createResourcesComponent){
        this.resource = resource;
        this.resourcesDataProvider = resourcesDataProvider;
        this.grid = grid;
        this.navbarAdmin = navbarAdmin;
        this.adminView = adminView;
        this.createResourcesComponent = createResourcesComponent;
        //UI initialization
        editLabel = new Label("Edit resource");
        if(resource.getName()!=null) nameField.setValue(resource.getName())
                ;
        else nameField.setPlaceholder("not set")
                ;
        if(resource.getReference()!=null) referenceField.setValue(resource.getReference())
                ;
        else referenceField.setPlaceholder("not set")
                ;
        submitButton = new Button("Edit", event->{
            this.editResource();
        });
        this.add(editLabel , nameField, referenceField, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void  editResource(){
        boolean edited = resourcesDataProvider.editResource(String.valueOf(resource.getId()), nameField.getValue(), referenceField.getValue());
        if(!edited) Notification.show("Server error resource was not updated");
        grid.getDataProvider().refreshItem(resource);
        adminView.removeAll();
        adminView.add(navbarAdmin, createResourcesComponent, grid);
    }

}
