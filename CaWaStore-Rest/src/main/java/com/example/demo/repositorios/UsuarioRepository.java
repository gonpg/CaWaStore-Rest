package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.Usuario;


import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByNombreUsuario(String nombreUsuario);

}