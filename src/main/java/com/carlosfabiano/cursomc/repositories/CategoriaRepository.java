package com.carlosfabiano.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfabiano.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	
	
}
