package com.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Control;


public interface ControlRepository extends PagingAndSortingRepository<Control, Long>{
	
	public Control findByCodControl(Long codControl);
			
	@Query("select c from Control c where c.animal.codAnimal=?1")
	public List<Control> findByCodAnimal(Long codAnimal);

}
