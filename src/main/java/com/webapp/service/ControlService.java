package com.webapp.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Control;

public interface ControlService {

	public List<Control> findAll();
	
	public Page<Control> findAll(Pageable pageable);
	
	public void save(Control control);
	
	public Control findByCodControl(Long codControl);
	
	public void delete(Long codControl);
	
	public List<Control> findByCodAnimal(Long codAnimal);
	
}
