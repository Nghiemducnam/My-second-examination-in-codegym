package com.codegym.service.impl;

import com.codegym.model.City;
import com.codegym.repository.CityRepository;
import com.codegym.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.delete(id);
    }
}