package com.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int codParto;
	
	@Column(name ="fecha_parto")
	private Date fechaParto;
	
	@Column(name ="nro_crias")
	private int nroCrias;
	
	@Column(name ="fecha_proximo_celo")
	private Date fechaProximoCelo;

	public int getCodParto() {
		return codParto;
	}

	public void setCodParto(int codParto) {
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
