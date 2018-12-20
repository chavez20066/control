package com.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Padrillo;

public interface PadrilloService {

	public List<Padrillo> findPadrilloAllOrderByNombre();
	
	public Padrillo findBycodPadrillo(Long codPadrillo);
	
	public List<Padrillo> findAll();
	
	public Page<Padrillo> findAll(Pageable pageable);
	
	public void save(Padrillo padrillo);

	public void delete(Long codPadrillo);
}
