package com.example.demo.repositorios;

import com.example.demo.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNombreIsLike(String nombre);

}

