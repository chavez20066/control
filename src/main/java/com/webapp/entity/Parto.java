package com.webapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parto")
public class Parto {

	private int cod_part;
	private Date fecha_parto;
	private int nro_crias;
	
	private Date fecha_proximo_celo;
	
	
	private Animales animal;
	
}
