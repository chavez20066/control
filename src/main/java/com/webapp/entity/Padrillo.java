package com.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name="padrillo")
public class Padrillo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_padrillo")
	private Long codPadrillo;
	
	@Column(name ="nombre")
	private String nombre;
	
	@Column(name ="descripcion")
	private String descripcion;
	
	//@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "padrillo_cod_padrillo")
	private List<Animal> animales;
	

	
	
	public Padrillo() {
		animales=new ArrayList<Animal>();		
	}
	
	public void addAnimal(Animal animal) {
		animales.add(animal);
	}

	public Long getCodPadrillo() {
		return codPadrillo;
	}

	public void setCodPadrillo(Long codPadrillo) {
		this.codPadrillo = codPadrillo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}

	@Override
	public String toString() {
		return "Padrillo [codPadrillo=" + codPadrillo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", animales=" + animales.size() + "]";
	}
	
	
	

}
