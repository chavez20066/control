package com.webapp.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapp.entity.Evento;

@Repository("eventoRepository")
public interface EventoRepository extends JpaRepository<Evento, Serializable>{

	@Query("select e from Evento e order by e.descripcion")
	public List<Evento> findAllOrderByDescripcion();
	
	public Evento findByCodEvento(Long codEvento);
}
