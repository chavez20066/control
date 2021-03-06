package com.webapp.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Animal;

public interface AnimalService {

	public List<Animal> findAll();
	
	public List<Animal> findByNombreAndCodAnimal(String term);
	
	public Page<Animal> findAll(Pageable pageable);
	
	public List<Animal> finAllOrderByNombre();
	
	public void save(Animal animal);
	
	public Animal findByCodAnimal(Long codAnimal);
	
	public void delete(Long codAnimal);
	
	
	
}
