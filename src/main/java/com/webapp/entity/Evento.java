package com.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="evento")
public class Evento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_evento")
	private Long codEvento;
	
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

	public Long getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Long codEvento) {
		this.codEvento = codEvento;
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

	@Override
	public String toString() {
		return "Evento [codEvento=" + codEvento + ", descripcion=" + descripcion + ", controles=" + controles.size() + "]";
	}
	
	
	
	
	
	

}
