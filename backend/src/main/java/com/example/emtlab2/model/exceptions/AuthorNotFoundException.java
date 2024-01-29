package com.example.emtlab2.model.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException() {
        super("No author with this id is found.");
    }
}
