package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//primary key PK
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String genre;
    @ManyToOne
    @JoinColumn(name = "author_id")  //foreign key FK
    private Author author;

    public Book(Long id, String title, Double price, Author author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }
}
