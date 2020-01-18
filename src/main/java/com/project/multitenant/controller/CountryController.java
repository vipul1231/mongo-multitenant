package com.project.multitenant.controller;

import com.project.multitenant.model.Country;
import com.project.multitenant.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @PostMapping("/country")
    public ResponseEntity<Boolean> createCountry (@RequestBody Country country){
        countryRepository.saveCountry(country);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
