package com.codegym.repository;

import com.codegym.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
