package com.webapp.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Produccion;

public interface ProduccionService {

	public List<Produccion> findAll();
	
	public Page<Produccion> findAll(Pageable pageable);
	
	public void save(Produccion produccion);
	
	public Produccion findByCodProduccion(Long codProduccion);
	
	public void delete(Long codProduccion);
	
	public List<Produccion> findByCodAnimal(Long codAnimal);
	
}
