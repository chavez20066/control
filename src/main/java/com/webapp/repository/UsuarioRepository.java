package com.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	public Usuario findByUsername(String username);

}
