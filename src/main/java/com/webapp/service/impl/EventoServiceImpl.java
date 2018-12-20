package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.entity.Evento;
import com.webapp.repository.EventoRepository;
import com.webapp.service.EventoService;


@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Override
	public List<Evento> findEventoAllOrderByDescripcion() {		
		return eventoRepository.findAllOrderByDescripcion();
	}

	@Override
	public Evento findByCodEvento(Long codEvento) {
		// TODO Auto-generated method stub
		return eventoRepository.findByCodEvento(codEvento);
	}

	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return (List<Evento>) eventoRepository.findAll();
	}

	@Override
	public Page<Evento> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return eventoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Evento evento) {
		// TODO Auto-generated method stub
		eventoRepository.save(evento);
		
	}

	@Override
	public void delete(Long codEvento) {
		// TODO Auto-generated method stub
		eventoRepository.deleteById(codEvento);
		
	}

	

	
}
