package com.matricula.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.entity.Alumno;
import com.matricula.repository.IAlumnoRepository;
import com.matricula.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoRepository aR;
	
	@Override
	public void insert(Alumno alumno) {
		aR.save(alumno);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		aR.deleteById(id);
	}

	@Override
	public Alumno getAlumno(Long id) {
		// TODO Auto-generated method stub
		Optional<Alumno> alumno = aR.findById(id);
		return alumno.get();
	}

	@Override
	public List<Alumno> listar() {
		// TODO Auto-generated method stub
		
		return aR.findAll();
	}

	
	
	
	
	@Override
	public List<Alumno> listarXDni(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> listarXnombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
