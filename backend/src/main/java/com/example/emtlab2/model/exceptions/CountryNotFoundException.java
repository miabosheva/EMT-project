package com.example.emtlab2.model.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException() {
        super("No country with this id is found.");
    }
}
