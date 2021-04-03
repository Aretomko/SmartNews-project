package com.example.application.views.admin.resources;

import com.example.application.domain.Resource;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ResourcesMainViewAdmin extends VerticalLayout {
    private final CreateResourceGridService createResourceGridService;


    private final NavbarAdmin navbarAdmin;
    private Grid<Resource> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public ResourcesMainViewAdmin(CreateResourceGridService createResourceGridService){
        this.createResourceGridService = createResourceGridService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createResourceGridService.createGrid();
        CreateResourcesComponent createResourcesComponent = new CreateResourcesComponent();
        this.modificationComponentDisplayed = createResourcesComponent;
        grid.addItemClickListener(item-> this.itemClickEvent());
        this.displayUiComponents();
    }
    private void itemClickEvent(){
        EditResourceComponent editResourceComponent = new EditResourceComponent();
        this.modificationComponentDisplayed = editResourceComponent;
        this.removeAll();
        this.displayUiComponents();
    }

    private void displayUiComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
