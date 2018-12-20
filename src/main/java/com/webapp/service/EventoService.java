package com.webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.entity.Evento;

public interface EventoService {

	public List<Evento> findEventoAllOrderByDescripcion();
	
	public Evento findByCodEvento(Long codEvento);
	
	public List<Evento> findAll();
	
	public Page<Evento> findAll(Pageable pageable);
	
	public void save(Evento evento);

	public void delete(Long codEvento);
}
