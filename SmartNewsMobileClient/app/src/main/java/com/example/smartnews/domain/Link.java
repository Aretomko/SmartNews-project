package com.example.smartnews.domain;

public class Link {
    private String url;
    private int likes;

    public Link() {
    }

    public Link(String url, int likes) {
        this.url = url;
        this.likes = likes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
