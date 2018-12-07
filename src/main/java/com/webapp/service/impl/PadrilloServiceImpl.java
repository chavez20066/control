package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
