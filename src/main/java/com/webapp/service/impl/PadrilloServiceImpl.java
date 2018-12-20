package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.entity.Padrillo;
import com.webapp.repository.PadrilloRepository;
import com.webapp.service.PadrilloService;


@Service
public class PadrilloServiceImpl implements PadrilloService {

	@Autowired
	private PadrilloRepository padrilloRepository;
	
	@Override
	public List<Padrillo> findPadrilloAllOrderByNombre() {		
		return padrilloRepository.findAllOrderByNombre();
	}

	@Override
	public Padrillo findBycodPadrillo(Long codPadrillo) {
		// TODO Auto-generated method stub
		return padrilloRepository.findByCodPadrillo(codPadrillo);
	}

	@Override
	public List<Padrillo> findAll() {
		// TODO Auto-generated method stub
		return (List<Padrillo>) padrilloRepository.findAll();
	}

	@Override
	public Page<Padrillo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return padrilloRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Padrillo padrillo) {
		// TODO Auto-generated method stub
		padrilloRepository.save(padrillo);
		
	}

	@Override
	public void delete(Long codPadrillo) {
		// TODO Auto-generated method stub
		padrilloRepository.deleteById(codPadrillo);
		
	}

	

	
}
