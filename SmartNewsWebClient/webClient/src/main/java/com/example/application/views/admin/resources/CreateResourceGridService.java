package com.example.application.views.admin.resources;

import com.example.application.dataProvider.ResourcesDataProvider;
import com.example.application.domain.domain.Resource;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class CreateResourceGridService {
    private final ResourcesDataProvider dataProvider;
    private Grid<Resource> grid;

    public CreateResourceGridService(ResourcesDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Grid<Resource> createGrid(){
        grid = new Grid<Resource>();
        grid.setItems(dataProvider.getAllResources());
        grid.addColumn(Resource::getName).setHeader("Name");
        grid.addColumn(Resource::getReference).setHeader("Reference");
        return grid;
    }
}
