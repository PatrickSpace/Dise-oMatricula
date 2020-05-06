package com.matricula.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.entity.Matricula;
import com.matricula.repository.IMatriculaRepository;
import com.matricula.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService{

	@Autowired
	private IMatriculaRepository mR;
	
	@Override
	public void insert(Matricula matricula) {
		// TODO Auto-generated method stub
		mR.save(matricula);
	}

	@Override
	public void elminar(Long id) {
		// TODO Auto-generated method stub
		mR.deleteById(id);
	}

	@Override
	public Matricula getMatricula(Long id) {
		// TODO Auto-generated method stub
		Optional<Matricula> mat = mR.findById(id);
		return mat.get();
	}

	@Override
	public List<Matricula> listar() {
		return mR.findAll();
	}

	@Override
	public List<Matricula> listarciclo(int ciclo) {
		return mR.listarxciclo(ciclo);
	}

	
}
