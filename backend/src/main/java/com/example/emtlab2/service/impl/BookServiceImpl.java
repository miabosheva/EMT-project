package com.example.emtlab2.service.impl;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Book;
import com.example.emtlab2.model.dto.BookDto;
import com.example.emtlab2.model.enumerations.Category;
import com.example.emtlab2.model.exceptions.AuthorNotFoundException;
import com.example.emtlab2.model.exceptions.BookNotFoundException;
import com.example.emtlab2.model.exceptions.InsufficientNumberOfCopiesException;
import com.example.emtlab2.repository.BookRepository;
import com.example.emtlab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorServiceImpl authorService;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorServiceImpl authorService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorService.findById(authorId).orElseThrow(AuthorNotFoundException::new);
        Book book = new Book(name,category,author,availableCopies);
        this.bookRepository.save(book);
    }

    @Override
    public void deleteById(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        this.bookRepository.delete(book);
    }

    @Override
    public void edit(Long bookId,String name,Category category,Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Author author = this.authorService.findById(authorId).orElseThrow(AuthorNotFoundException::new);

        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        Author author = this.authorService.findById(bookDto.getAuthorId()).orElseThrow(AuthorNotFoundException::new);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setAuthor(author);
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Book book = new Book();
        Author author = this.authorService.findById(bookDto.getAuthorId()).orElseThrow(AuthorNotFoundException::new);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setAuthor(author);
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void markAsTaken(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        if(book.getAvailableCopies()>0){
            book.setAvailableCopies(book.getAvailableCopies()-1);
        }else{
            throw new InsufficientNumberOfCopiesException();
        }

        this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.of(this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }
}
