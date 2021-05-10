package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PromocionRepository extends JpaRepository<Promocion, Long> {

	  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	    Promocion save(Promocion promocion);

	    @Transactional(readOnly = true)
    Promocion findByProductoId(Long id);

}

