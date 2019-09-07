package com.carlosfabiano.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfabiano.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	
	
}
