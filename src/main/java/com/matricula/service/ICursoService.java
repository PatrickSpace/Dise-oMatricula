package com.matricula.service;

import java.util.List;

import com.matricula.entity.Curso;

public interface ICursoService {
 
	public void insert(Curso curso);
	
	public void eliminar(Long id);
	
	public Curso getCurso(Long id);
	
	public List<Curso> listar();
}
