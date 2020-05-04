package com.matricula.service;

import java.util.List;

import com.matricula.entity.Profesor;

public interface IProfesorService {

	public void insert(Profesor profe);
	
	public void eliminar (Long id);
	
	public Profesor getProfesor(Long id);
	
	public List<Profesor> listar();
	
	
}
