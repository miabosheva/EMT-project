package com.example.emtlab2.model.dto;

import com.example.emtlab2.model.Country;
import lombok.Data;

import javax.persistence.OneToOne;
@Data
public class AuthorDto {
    private String name;

    private String surname;

    @OneToOne
    private Country country;

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public AuthorDto() {

    }
}
