package com.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
