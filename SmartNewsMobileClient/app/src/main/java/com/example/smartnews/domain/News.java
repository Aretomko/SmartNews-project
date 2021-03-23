package com.example.smartnews.domain;
import java.util.Set;

public class News {
    private String heading;
    private Set<Link> links;

    public News() {
    }

    public News(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }
}
