package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	

}
