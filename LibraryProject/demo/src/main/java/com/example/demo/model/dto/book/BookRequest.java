package com.example.demo.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookRequest {

    @NotBlank(message = "Book title is Required")
    @Size(min = 2, max = 200)
    private String title;

    @NotBlank(message = "Book genre is Required")
    @Size(min = 2, max = 200)
    private String genre;

    @NotNull(message = "Book price is Required")
    @Positive(message = "Price must be more than zero")
    private Double price;

    @NotNull(message = "Book ID is Required")
    @Positive(message = "ID must be more than zero")
    private Long authorId;

    public BookRequest(String title, String genre, Double price, Long authorId) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.authorId = authorId;
    }
}
