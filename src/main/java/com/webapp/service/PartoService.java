package com.webapp.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Parto;

public interface PartoService {

	public List<Parto> findAll();
	
	public Page<Parto> findAll(Pageable pageable);
	
	public void save(Parto parto);
	
	public Parto findByCodParto(Long codParto);
	
	public void delete(Long codParto);
	
}
