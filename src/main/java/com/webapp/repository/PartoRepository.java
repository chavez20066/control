package com.webapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Parto;



public interface PartoRepository extends PagingAndSortingRepository<Parto, Long>{
	
	public Parto findByCodParto(Long codParto);

}
