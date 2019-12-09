package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class City implements Validator  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CityId;
    @NotEmpty
    private String cityName;
    @NotNull
    private Double area;
    @NotNull
    private Double population;
    @NotNull
    private Double gdp;
    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_Id")
    private Country country;

    public City(String cityName, Double area, Double population, Double gdp, String description, Country country) {
        this.cityName = cityName;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country;
    }

    public City() {
    }

    public Long getCityId() {
        return CityId;
    }

    public void setCityId(Long cityId) {
        CityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}