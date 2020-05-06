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
	private int ciclo;
	
	@ManyToOne
	@JoinColumn(name = "Profesro", nullable = false)
	private Profesor profesor;
	
	@ManyToOne
	@JoinColumn(name = "Curso", nullable = false)
	private Curso curso;
	
	@OneToMany
	@JoinColumn(name = "Alumnos", nullable = true)
	private List<Alumno> alumnos;

	
	public int addAlumno(Alumno al) {
		
		if(this.alumnos.size() < 11) {
			this.alumnos.add(al);
			return 1;
		}
		else
			return 0;
	}
	
	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Matricula(Long id, int ciclo, Profesor profesor, Curso curso, List<Alumno> alumnos) {
		super();
		this.id = id;
		this.ciclo = ciclo;
		this.profesor = profesor;
		this.curso = curso;
		this.alumnos = alumnos;
	}









	
	
	
}
