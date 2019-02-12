package com.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.webapp.entity.Produccion;


public interface ProduccionRepository extends PagingAndSortingRepository<Produccion, Long>{
	
	public Produccion findByCodProduccion(Long codProduccion);
	
	@Query("select p from Produccion p where p.animal.codAnimal=?1")
	public List<Produccion> findByCodAnimal(Long codAnimal);

}
