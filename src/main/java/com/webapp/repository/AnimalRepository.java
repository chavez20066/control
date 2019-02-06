package com.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Animal;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long>{

	@Query("select a from Animal a where a.sexo='HEMBRA' order by a.nombre")
	public List<Animal> findAllOrderByNombre();
	
	@Query("select a from Animal a left join fetch a.controles c  join fetch a.producciones p  where a.codAnimal=?1")
	public Animal findByCodAnimal(Long codAnimal);
	
	//public Animal findByCodAnimal(Long codAnimal);
	
	@Query("delete from Animal a where a.codAnimal=?1")	
	public void delete(Long CodAnimal);
	
	@Query("select a from Animal a where a.nombre like %?1% or a.codAnimal=?1")
	public List<Animal> findByNombreAndCodAnimal(String term);
}
