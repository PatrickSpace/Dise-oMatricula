package com.matricula.service;

import java.util.List;

import com.matricula.entity.Alumno;

public interface IAlumnoService {

	public void insert(Alumno alumno);

	public void eliminar(Long id);
	
	public Alumno getAlumno(Long id);

	public List<Alumno> listar();
	
	public List<Alumno> listarXDni(int dni);
	
	public List<Alumno> listarXnombre(String nombre);
	
}
