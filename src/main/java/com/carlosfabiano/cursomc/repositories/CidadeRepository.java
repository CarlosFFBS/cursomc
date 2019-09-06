package com.carlosfabiano.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfabiano.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	
	
}
