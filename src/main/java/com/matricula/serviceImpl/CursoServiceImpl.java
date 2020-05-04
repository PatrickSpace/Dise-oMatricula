package com.matricula.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.entity.Curso;
import com.matricula.repository.ICursoRepository;
import com.matricula.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoRepository cR;
	
	@Override
	public void insert(Curso curso) {
		// TODO Auto-generated method stub
		cR.save(curso);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		cR.deleteById(id);
	}

	@Override
	public Curso getCurso(Long id) {
		// TODO Auto-generated method stub
		Optional<Curso> curso = cR.findById(id);
		return curso.get();
	}

	@Override
	public List<Curso> listar() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

}
