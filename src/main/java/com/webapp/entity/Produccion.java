package com.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="produccion")
public class Produccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_produccion")
	private Long codProduccion;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name ="fecha_ordenio")
	private Date fechaOrdenio;
	
	@Column(name ="peso_produccion")
	private float pesoProduccion;
	
	@Column(name ="personal")
	private String personal;
		
	@Column(name ="turno")
	private String turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animales_cod_animal")
	private Animal animal;

	
	
	
	public Produccion() {
		
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Long getCodProduccion() {
		return codProduccion;
	}

	public void setCodProduccion(Long codProduccion) {
		this.codProduccion = codProduccion;
	}

	public Date getFechaOrdenio() {
		return fechaOrdenio;
	}

	public void setFechaOrdenio(Date fechaOrdeño) {
		this.fechaOrdenio = fechaOrdeño;
	}

	public float getPesoProduccion() {
		return pesoProduccion;
	}

	public void setPesoProduccion(float pesoProduccion) {
		this.pesoProduccion = pesoProduccion;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	
	
	

}
