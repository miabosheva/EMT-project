package com.example.emtlab2.service;

import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void create(String name, Category category, Long authorId, Integer availableCopies);

    void deleteById(Long bookId);

    void edit(Long bookId,String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    Optional<Book> create(BookDto bookDto);

    void markAsTaken(Long bookId);

    Optional<Book> findById(Long bookId);

    List<Book> findAll();
}
