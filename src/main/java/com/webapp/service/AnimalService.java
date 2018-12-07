package com.webapp.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Animal;

public interface AnimalService {

	public List<Animal> findAll();
	
	public Page<Animal> findAll(Pageable pageable);
	
	public List<Animal> finAllOrderByNombre();
	
}
