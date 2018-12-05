package com.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="animales")
public class Animales  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	private String sexo;
	
	private int cod_madre;
	
	private int cod_padre;
	
	private Date fecha_nacimiento;
	
	private String raza;
	
	private String metodo_concep;
	
	private String estado;
	
	
	
	
	
	

}
