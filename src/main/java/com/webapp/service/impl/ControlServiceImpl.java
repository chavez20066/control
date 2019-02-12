package com.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.entity.Control;
import com.webapp.repository.ControlRepository;
import com.webapp.service.ControlService;

@Service
public class ControlServiceImpl implements ControlService {

	@Autowired
	private ControlRepository controlRepository;
	
	
	@Override
	public List<Control> findAll() {
		// TODO Auto-generated method stub
		return (List<Control>) controlRepository.findAll();
	}

	@Override
	public Page<Control> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return controlRepository.findAll(pageable);
	}

	
	@Override
	public void save(Control control) {
		// TODO Auto-generated method stub
		
		controlRepository.save(control);
		
	}

	@Override
	public Control findByCodControl(Long codControl) {
		// TODO Auto-generated method stub
		return controlRepository.findByCodControl(codControl);
	}

	@Override
	public void delete(Long codControl) {
		// TODO Auto-generated method stub
		controlRepository.deleteById(codControl);
		
	}

	@Override
	public List<Control> findByCodAnimal(Long codAnimal) {
		// TODO Auto-generated method stub
		return controlRepository.findByCodAnimal(codAnimal);
	}

	/*@Override
	public Page<Control> findByCodAnimal(Pageable pageable, Long codAnimal) {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
