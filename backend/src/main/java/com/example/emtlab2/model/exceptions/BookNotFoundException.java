package com.example.emtlab2.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("No book with this id is found");
    }
}
