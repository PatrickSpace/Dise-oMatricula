package com.matricula.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Matricula")
public class Matricula implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ciclo", nullable = false)
	private String ciclo;
	
	@Column(name = "year", nullable = false)
	private int year;
	
	@ManyToOne
	@JoinColumn(name = "Docente", nullable = false)
	private Profesor docente;
	
	@ManyToOne
	@JoinColumn(name = "Curso", nullable = false)
	private Curso curso;
	
	@OneToMany
	@JoinColumn(name = "AlumnosXmatricula", nullable = true)
	private List<AlumnosXmatricula> alumnos;

	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Matricula(Long id, String ciclo, int year, Profesor docente, Curso curso, List<AlumnosXmatricula> alumnos) {
		super();
		this.id = id;
		this.ciclo = ciclo;
		this.year = year;
		this.docente = docente;
		this.curso = curso;
		this.alumnos = alumnos;
	}


	public List<AlumnosXmatricula> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnosXmatricula> alumnos) {
		this.alumnos = alumnos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Profesor getDocente() {
		return docente;
	}

	public void setDocente(Profesor docente) {
		this.docente = docente;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	
	
	
}
