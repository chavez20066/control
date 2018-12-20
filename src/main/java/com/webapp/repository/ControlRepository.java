package com.webapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.webapp.entity.Control;


public interface ControlRepository extends PagingAndSortingRepository<Control, Long>{
	
	public Control findByCodControl(Long codControl);

}
