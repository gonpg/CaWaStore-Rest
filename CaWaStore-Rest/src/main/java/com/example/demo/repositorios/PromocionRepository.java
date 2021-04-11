package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.*;


public interface PromocionRepository extends JpaRepository<Promocion, Long> {

    Promocion findByProductoId(Long id);

}

