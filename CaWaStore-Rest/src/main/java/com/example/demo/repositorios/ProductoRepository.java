package com.example.demo.repositorios;

import com.example.demo.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Producto save(Producto producto);

    @Transactional(readOnly = true)
    List<Producto> findAll();

    @Transactional(readOnly = true)
    Optional<Producto> findById(Long id);

    @Transactional(readOnly = true)
    Producto findByNombre(String nombre);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNombreIsLike(String nombre);

}

