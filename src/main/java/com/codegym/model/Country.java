package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Country implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String countryName;

    @OneToMany(targetEntity = City.class)
    private List<City> cities;

    public Country() {
    }

    public Country(String countryName, List<City> cities) {
        this.countryName = countryName;
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Country.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}