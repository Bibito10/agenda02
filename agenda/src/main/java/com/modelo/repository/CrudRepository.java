package com.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelo.model.Aluno;

public interface CrudRepository extends JpaRepository<Aluno, Long>{

}
