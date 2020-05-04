package com.matricula.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.entity.Profesor;
import com.matricula.repository.IProfesorRepository;
import com.matricula.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	private IProfesorRepository dR;
	
	@Override
	public void insert(Profesor docente) {
		// TODO Auto-generated method stub
		dR.save(docente);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		dR.deleteById(id);
	}

	@Override
	public Profesor getProfesor(Long id) {
		// TODO Auto-generated method stub
		Optional<Profesor> profe = dR.findById(id);
		return profe.get();
	}

	@Override
	public List<Profesor> listar() {
		return dR.findAll();
	}

}
