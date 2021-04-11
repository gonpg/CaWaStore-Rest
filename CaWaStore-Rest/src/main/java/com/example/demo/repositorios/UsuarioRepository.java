package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.*;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreusuario);

}