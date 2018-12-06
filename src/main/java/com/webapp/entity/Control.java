package com.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="control")
public class Control implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_control")
	private int codControl;
	
	@Column(name ="fecha_control")
	private Date fechaControl;
	
	@Column(name ="peso")
	private int peso;
	
	@Column(name ="observacion")
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Animal animal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Evento evento;
	
	
		

	public int getCodControl() {
		return codControl;
	}

	public void setCodControl(int codControl) {
		this.codControl = codControl;
	}

	public Date getFechaControl() {
		return fechaControl;
	}

	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
	
}
