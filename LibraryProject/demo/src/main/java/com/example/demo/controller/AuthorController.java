package com.example.demo.controller;

import com.example.demo.model.dto.author.AuthorResponse;
import com.example.demo.model.entity.Author;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/")
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/paginated")
    public Page<AuthorResponse> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return authorService.getAllPaginated(page, size);
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping("/")
    public Author creatAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }

    @PutMapping("/update/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.update(id,author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
    }

}
