package com.example.application.views.admin.news.sources;

import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.Source;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditSourceComponent extends HorizontalLayout {
    private final Source source;
    private final NewsDataProvider newsDataProvider;
    private final Grid<Source> grid;
    private final SourcesMainView mainView;
    private final NavbarAdmin navbarAdmin;
    private final CreateSourceComponent createSourceComponent;

    private Label editLabel;
    private TextField nameField;
    private TextField referenceField;
    private Button submitButton;

    public EditSourceComponent(Source source,
                               NewsDataProvider newsDataProvider,
                               Grid<Source> grid,
                               SourcesMainView mainView,
                               NavbarAdmin navbarAdmin,
                               CreateSourceComponent createSourceComponent){
        this.source = source;
        this.newsDataProvider = newsDataProvider;
        this.grid = grid;
        this.mainView = mainView;
        this.navbarAdmin = navbarAdmin;
        this.createSourceComponent = createSourceComponent;
        //UI initialization
        editLabel = new Label("Edit source");
        nameField = new TextField("source name");
        referenceField = new TextField("reference");
        submitButton = new Button("Edit", event->{
            this.editSource();
        });
        this.add(editLabel,nameField, referenceField, submitButton);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void editSource(){
        boolean edited = newsDataProvider.editSource(source.getId(), nameField.getValue(), referenceField.getValue());
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public void setReferenceField(TextField referenceField) {
        this.referenceField = referenceField;
    }
}
