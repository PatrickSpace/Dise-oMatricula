package com.matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.entity.Matricula;

@Repository
public interface IMatriculaRepository extends JpaRepository<Matricula, Long>{

}
