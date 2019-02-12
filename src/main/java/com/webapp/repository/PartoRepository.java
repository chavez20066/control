package com.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Parto;



public interface PartoRepository extends PagingAndSortingRepository<Parto, Long>{
	
	public Parto findByCodParto(Long codParto);
	
	@Query("select p from Parto p where p.animal.codAnimal=?1")
	public List<Parto> findByCodAnimal(Long codAnimal);

}
