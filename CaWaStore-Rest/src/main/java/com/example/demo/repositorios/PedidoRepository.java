package com.example.demo.repositorios;

import com.example.demo.entidades.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByUsuario(Usuario usuario);
} 