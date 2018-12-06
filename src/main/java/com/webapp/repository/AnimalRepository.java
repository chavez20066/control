package com.webapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Animal;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer>{

}
