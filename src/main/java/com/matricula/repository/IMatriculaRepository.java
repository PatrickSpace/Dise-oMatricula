package com.matricula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matricula.entity.Matricula;

@Repository
public interface IMatriculaRepository extends JpaRepository<Matricula, Long>{

	@Query(value = "select m from Matricula m where m.ciclo = ?1")
	public List<Matricula> listarxciclo(int ciclo);
	
}
