package com.example.demo.repositorios;

import com.example.demo.entidades.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	    Pedido save(Pedido pedido);

	    @Transactional(readOnly = true)
	    Optional<Pedido> findById(Long id);
} 