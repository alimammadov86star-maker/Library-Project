package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.dto.book.BookRequest;
import com.example.demo.model.dto.book.BookResponse;
import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // CRUD methods
    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public Book getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        return book;
    }

    public BookResponse create(BookRequest bookRequest) {

        if (bookRequest.getAuthorId() == null) {
            throw new BadRequestException("Author is required!");
        }

        Long authorId = bookRequest.getAuthorId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));


        Book book = new Book();

        book.setTitle(bookRequest.getTitle());
        book.setGenre(bookRequest.getGenre());
        book.setPrice(bookRequest.getPrice());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        return convertToResponse(savedBook);
    }

    public BookResponse update(Long id, BookRequest newBook) {

        Long authorId = newBook.getAuthorId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found: " + authorId));

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));

        book.setTitle(newBook.getTitle());
        book.setGenre(newBook.getGenre());
        book.setPrice(newBook.getPrice());
        book.setAuthor(author);


        Book updatedBook = bookRepository.save(book);

        return convertToResponse(updatedBook);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book is not found: " + id));
        bookRepository.delete(book);
    }

    // QUERY METHODS
    public List<Book> getByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getByGenre(String genre){
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Book> getByMinPrice(Double minPrice){
        return bookRepository.findByPriceGreaterThan(minPrice);
    }

    public List<Book> getByMaxPrice(Double maxPrice){
        return bookRepository.findByPriceLessThan(maxPrice);
    }


    public Page<BookResponse> getAllPaginated(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending() // 1,2,3,4,5... 100000
                : Sort.by(sortBy).ascending(); // 100,99,98, ...., -100

//        if (direction.equalsIgnoreCase("asc")) {
//            sort = Sort.by(sortBy).ascending();
//        } else {
//            sort = Sort.by(sortBy).descending();
//        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort); // показываем page = 0, size = 5

        Page<Book> books = bookRepository.findAll(pageable); // получаем

        return books.map(this::convertToResponse);
    }

    // DTO -> Entity
    private BookResponse convertToResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPrice(),
                book.getAuthor().getFullname()
        );
    }
}
