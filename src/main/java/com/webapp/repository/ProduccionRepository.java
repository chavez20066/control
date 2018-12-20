package com.webapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.webapp.entity.Produccion;


public interface ProduccionRepository extends PagingAndSortingRepository<Produccion, Long>{
	
	public Produccion findByCodProduccion(Long codProduccion);

}
