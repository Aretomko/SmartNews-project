package com.example.application.domain.domain;

import java.util.List;

public class News {
    private String id;
    private String heading;
    private List<Source> sources;
    private Category category;

    public News(String id, String heading, List<Source> sources, Category category) {
        this.id = id;
        this.heading = heading;
        this.sources = sources;
        this.category = category;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
