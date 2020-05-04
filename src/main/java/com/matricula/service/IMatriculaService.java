package com.matricula.service;

import java.util.List;

import com.matricula.entity.Matricula;

public interface IMatriculaService {

	public void insert(Matricula matricula);
	
	public void elminar(Long id);
	
	public Matricula getMatricula(Long id);
	
	public List<Matricula> listar();
}
