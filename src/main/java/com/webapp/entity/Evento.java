package com.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Evento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_evento")
	private int evento;
	
	@Column(name ="descripcion")
	private String descripcion;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "evento_cod_evento")
	private List<Control> controles;


	
	
	public Evento() {
		
		controles=new ArrayList<Control>();
	}
	
	public void addControl(Control control) {
		
		controles.add(control);
	}


	public int getEvento() {
		return evento;
	}


	public void setEvento(int evento) {
		this.evento = evento;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Control> getControles() {
		return controles;
	}


	public void setControles(List<Control> controles) {
		this.controles = controles;
	}
	
	
	
	
	
	

}
