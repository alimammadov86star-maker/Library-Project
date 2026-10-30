package com.example.demo.controller;

import com.example.demo.model.dto.book.BookRequest;
import com.example.demo.model.dto.book.BookResponse;
import com.example.demo.model.entity.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public List<BookResponse> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/paginated")
    public Page<BookResponse> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return bookService.getAllPaginated(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/")
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        return bookService.create(bookRequest);
    }

    @PutMapping("/update/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return bookService.update(id,bookRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    // Query Methods
    @GetMapping("/search/{title}")
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.getByTitle(title);
    }

    @GetMapping("/search/{genre}")
    public List<Book> getBooksByGenre(@RequestParam String genre) {
        return bookService.getByGenre(genre);
    }

    @GetMapping("/price/greater")
    public List<Book> getBooksByMinPrice(@RequestParam Double price) {
        return bookService.getByMinPrice(price);
    }

    @GetMapping("/price/less")
    public List<Book> getBooksByMaxPrice(@RequestParam Double price) {
        return bookService.getByMaxPrice(price);
    }
}
