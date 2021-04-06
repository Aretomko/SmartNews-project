package com.example.demo.domain;

import javax.persistence.*;
import java.net.InetAddress;

@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String reference;
    private Integer likes;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

}
