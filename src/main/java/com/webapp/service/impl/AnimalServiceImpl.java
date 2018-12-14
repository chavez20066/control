package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.entity.Animal;
import com.webapp.repository.AnimalRepository;
import com.webapp.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService{
	
	@Autowired
	private AnimalRepository animalRepository;

	@Override
	public List<Animal> findAll() {
		// TODO Auto-generated method stub
		return (List<Animal>) animalRepository.findAll();
	}

	@Override
	public Page<Animal> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return animalRepository.findAll(pageable);
	}

	
	@Override
	public List<Animal> finAllOrderByNombre() {
		// TODO Auto-generated method stub
		return animalRepository.findAllOrderByNombre();
	}

	@Override
	@Transactional
	public void save(Animal animal) {
		// TODO Auto-generated method stub
		animalRepository.save(animal);
		
	}

	@Override
	public Animal findByCodAnimal(Long codAnimal) {
		// TODO Auto-generated method stub
		return animalRepository.findByCodAnimal(codAnimal);
	}
	

}
