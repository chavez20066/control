package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.entity.Parto;
import com.webapp.repository.PartoRepository;
import com.webapp.service.PartoService;

@Service
public class PartoServiceImpl implements PartoService {

	@Autowired
	private PartoRepository partoRepository;
	
	
	@Override
	public List<Parto> findAll() {
		// TODO Auto-generated method stub
		return (List<Parto>) partoRepository.findAll();
	}

	@Override
	public Page<Parto> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return partoRepository.findAll(pageable);
	}

	
	@Override
	public void save(Parto Parto) {
		// TODO Auto-generated method stub
		
		partoRepository.save(Parto);
		
	}

	@Override
	public Parto findByCodParto(Long codParto) {
		// TODO Auto-generated method stub
		return partoRepository.findByCodParto(codParto);
	}

	@Override
	public void delete(Long codParto) {
		// TODO Auto-generated method stub
		partoRepository.deleteById(codParto);
		
	}

	@Override
	public List<Parto> findByCodAnimal(Long codAnimal) {
		// TODO Auto-generated method stub
		return partoRepository.findByCodAnimal(codAnimal);
	}
	

}
