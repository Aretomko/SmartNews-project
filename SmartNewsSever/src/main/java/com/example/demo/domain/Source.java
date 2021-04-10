package com.example.demo.domain;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Objects;

@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String reference;
    private Integer likes;
    @ManyToOne
    @JoinColumn(name="news_id")
    private News news;

    public Source() {
    }

    public Source(String name, String reference, News news) {
        this.name = name;
        this.reference = reference;
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reference, likes, news);
    }
}
