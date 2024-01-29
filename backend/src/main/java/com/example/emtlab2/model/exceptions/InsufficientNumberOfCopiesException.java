package com.example.emtlab2.model.exceptions;

public class InsufficientNumberOfCopiesException extends RuntimeException {
    public InsufficientNumberOfCopiesException() {
        super("There are no more copies of this book.");
    }
}
