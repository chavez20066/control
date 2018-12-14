package com.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="animales")
public class Animal  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name ="cod_animal")
	private Long codAnimal;
	
	@Column(name ="nombre", unique=true)
	private String nombre;
	
	@Column(name ="sexo")
	private String sexo;
	
	/*@Column(name ="cod_madre")
	private int codMadre;*/	
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name ="fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name ="raza")
	private String raza;
	
	@Column(name ="metodo_concep")
	private String metodoConcep;
	
	@Column(name ="estado")
	private String estado;
	
	@Column(name ="foto")
	private String foto;	
		
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "animales_cod_animal")
	private List<Parto> partos;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "animales_cod_animal")
	private List<Produccion> producciones;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "animales_cod_animal")
	private List<Control> controles;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cod_madre")
	private Animal madre;
			
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="padrillo_cod_padrillo")
	private Padrillo padrillo;
	
	
	public Animal() {
		
		partos=new ArrayList<Parto>();
		producciones=new ArrayList<Produccion>();
		controles=new ArrayList<Control>();
		//madre=new Animal();
	}
	
	
	
	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public Padrillo getPadrillo() {
		return padrillo;
	}

	public void setPadrillo(Padrillo padrillo) {
		this.padrillo = padrillo;
	}


	public List<Produccion> getProducciones() {
		return producciones;
	}



	public void setProducciones(List<Produccion> producciones) {
		this.producciones = producciones;
	}



	public Animal getMadre() {
		return madre;
	}



	public void setMadre(Animal madre) {
		this.madre = madre;
	}


	public void addParto(Parto parto) {
		partos.add(parto);		
	}
	public void addProducciones(Produccion produccion) {
		producciones.add(produccion);		
	}
	public void addControles(Control control) {
		controles.add(control);		
	}

	public Long getCodAnimal() {
		return codAnimal;
	}

	public void setCodAnimal(Long codAnimal) {
		this.codAnimal = codAnimal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getMetodoConcep() {
		return metodoConcep;
	}

	public void setMetodoConcep(String metodoConcep) {
		this.metodoConcep = metodoConcep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Parto> getPartos() {
		return partos;
	}

	public void setPartos(List<Parto> partos) {
		this.partos = partos;
	}

	public List<Produccion> getProduciones() {
		return producciones;
	}

	public void setProduciones(List<Produccion> produciones) {
		this.producciones = produciones;
	}

	public List<Control> getControles() {
		return controles;
	}

	public void setControles(List<Control> controles) {
		this.controles = controles;
	}



	@Override
	public String toString() {
		return "Animal [codAnimal=" + codAnimal + ", nombre=" + nombre + ", sexo=" + sexo + ", fechaNacimiento="
				+ fechaNacimiento + ", raza=" + raza + ", metodoConcep=" + metodoConcep + ", estado=" + estado
				+ ", foto=" + foto + ", partos=" + partos.size() + ", producciones=" + producciones.size() + ", controles="
				+ controles.size() + ", madre=" + madre.nombre + ", padrillo=" + padrillo.getNombre() + "]";
	}
	
	

}
