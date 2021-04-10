package com.example.demo.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String heading;

    @OneToMany(mappedBy="news", fetch = FetchType.EAGER)
    private List<Source> sources;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public News() {
    }

    public News(String heading, Category category) {
        this.heading = heading;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
