package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

	  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	    Resena save(Resena resena);

}