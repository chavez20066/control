package com.webapp.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapp.entity.Padrillo;

@Repository("padrilloRepository")
public interface PadrilloRepository extends JpaRepository<Padrillo, Serializable> {

	@Query("select p from Padrillo p order by p.nombre")
	public List<Padrillo> findAllOrderByNombre();
	
	public Padrillo findByCodPadrillo(Long codPadrillo);
	
}
