package com.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Animal;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long>{

	@Query("select a from Animal a where a.sexo='HEMBRA' order by a.nombre")
	public List<Animal> findAllOrderByNombre();
	
	public Animal findByCodAnimal(Long codAnimal);
}
