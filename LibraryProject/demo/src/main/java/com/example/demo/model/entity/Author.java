package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String style;

    public Author(Long id, String fullname, String country) {
        this.id = id;
        this.fullname = fullname;
        this.country = country;
    }
}
