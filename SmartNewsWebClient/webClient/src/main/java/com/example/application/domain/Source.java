package com.example.application.domain;

public class Source {
    private String id;
    private String name;
    private String reference;
    private int likes;

    public Source(String id, String name, String reference, int likes) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
