package com.matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.entity.Alumno;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Long>{

}
