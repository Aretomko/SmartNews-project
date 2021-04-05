package com.example.application.views.admin.resources;

import com.example.application.dataProviders.ResourcesDataProvider;
import com.example.application.domain.Resource;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "resources")
public class ResourcesMainViewAdmin extends VerticalLayout {
    private final ResourcesDataProvider resourcesDataProvider;

    private final NavbarAdmin navbarAdmin;
    private Grid<Resource> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public ResourcesMainViewAdmin(CreateResourceGridService createResourceGridService,
                                  ResourcesDataProvider resourcesDataProvider){
        this.resourcesDataProvider = resourcesDataProvider;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createResourceGridService.createGrid();
        CreateResourcesComponent createResourcesComponent = new CreateResourcesComponent(this.resourcesDataProvider, grid);
        this.modificationComponentDisplayed = createResourcesComponent;
        grid.addItemClickListener(item-> this.itemClickEvent(item.getItem(), createResourcesComponent));
        this.displayUiComponents();
    }
    private void itemClickEvent(Resource resource, CreateResourcesComponent createResourcesComponent){
        this.modificationComponentDisplayed = new EditResourceComponent(resource, this.resourcesDataProvider, grid, this, navbarAdmin,createResourcesComponent );
        this.removeAll();
        this.displayUiComponents();
    }

    private void displayUiComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
