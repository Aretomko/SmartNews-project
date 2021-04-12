package com.example.application.views.user;

import com.example.application.dataProviders.InternationalizationProvider;
import com.example.application.domain.Source;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SourceComponent extends VerticalLayout {
    //UI components
    private Image displayImage;
    private Label name;
    private Button read;
    private Label likes;

    public SourceComponent(InternationalizationProvider internationalizationProvider,
                           Source source){
        displayImage = new Image("https://sklquest.com/static/uploads/like.png", "empty hard");
        displayImage.setHeight("30px");
        displayImage.setWidth("30px");
        name = new Label(source.getName());
        read  =new Button("Read" , event->{
            UI.getCurrent().getPage().executeJavaScript("window.open(\""+source.getReference()+"\", \"_blank\");");
        });
        likes = new Label(String.valueOf(source.getLikes()));
        HorizontalLayout layout =new  HorizontalLayout();
        layout.add(displayImage, likes, name, read);
        layout.setWidth("100%");
        layout.setAlignItems(Alignment.BASELINE);
        this.add(layout);
    }
}
