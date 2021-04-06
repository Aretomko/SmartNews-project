package com.example.demo.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String heading;

    @OneToMany(mappedBy="news")
    private Set<Source> sources;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
