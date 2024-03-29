package com.carlosfabiano.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfabiano.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByEmail(String email);
	
}
