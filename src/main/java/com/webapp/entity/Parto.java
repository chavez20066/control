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
@Table(name="parto")
public class Parto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_parto")
	private Long codParto;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name ="fecha_parto")
	private Date fechaParto;
	
	@Column(name ="nro_crias")
	private int nroCrias;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name ="fecha_proximo_celo")
	private Date fechaProximoCelo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animales_cod_animal")
	private Animal animal;
	
	

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Long getCodParto() {
		return codParto;
	}

	public void setCodParto(Long codParto) {
		this.codParto = codParto;
	}

	public Date getFechaParto() {
		return fechaParto;
	}

	public void setFechaParto(Date fechaParto) {
		this.fechaParto = fechaParto;
	}

	public int getNroCrias() {
		return nroCrias;
	}

	public void setNroCrias(int nroCrias) {
		this.nroCrias = nroCrias;
	}

	public Date getFechaProximoCelo() {
		return fechaProximoCelo;
	}

	public void setFechaProximoCelo(Date fechaProximoCelo) {
		this.fechaProximoCelo = fechaProximoCelo;
	}
			
}
