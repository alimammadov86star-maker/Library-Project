package com.example.demo.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenreContainingIgnoreCase(String genre);
    List<Book> findByPriceGreaterThan(Double price); // price < x
    List<Book> findByPriceLessThan(Double price); // price > x

    Page<Book> findAll(Pageable pageable);
}
