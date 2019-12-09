package com.codegym.repository;

import com.codegym.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
}
