package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.entity.Produccion;
import com.webapp.repository.ProduccionRepository;
import com.webapp.service.ProduccionService;

@Service
public class ProduccionServiceImpl implements ProduccionService {

	@Autowired
	private ProduccionRepository produccionRepository;
	
	
	@Override
	public List<Produccion> findAll() {
		// TODO Auto-generated method stub
		return (List<Produccion>) produccionRepository.findAll();
	}

	@Override
	public Page<Produccion> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return produccionRepository.findAll(pageable);
	}

	
	@Override
	public void save(Produccion produccion) {
		// TODO Auto-generated method stub
		
		produccionRepository.save(produccion);
		
	}

	@Override
	public Produccion findByCodProduccion(Long codProduccion) {
		// TODO Auto-generated method stub
		return produccionRepository.findByCodProduccion(codProduccion);
	}

	@Override
	public void delete(Long codProduccion) {
		// TODO Auto-generated method stub
		produccionRepository.deleteById(codProduccion);
		
	}
	

}
