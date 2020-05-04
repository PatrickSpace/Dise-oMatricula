package com.matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.entity.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long>{

}
