package com.example.demo.entidades;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Usuario {
	
	  @ElementCollection(fetch = FetchType.EAGER)
	     private List<String> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String nombreUsuario;

    @Column(unique = true)
    private String email;

    
    private String contrasenya;

    @Column(unique = true)
    private String tarjetaCredito;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas;

    protected Usuario() {
    }

    public Usuario(String nombreUsuario, String email, String contrasenya, String... roles) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenya = new BCryptPasswordEncoder().encode(contrasenya);
        this.roles = new ArrayList<>(Arrays.asList(roles));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

  
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void anyadirPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void eliminarPeido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    public List<Resena> getResena() {
        return resenas;
    }

    public void setResena(List<Resena> resena) {
        this.resenas = resena;
    }

    public void anyadirResena(Resena resena) {
        this.resenas.add(resena);
    }

    public void eliminarResena(Resena resena) {
        this.resenas.remove(resena);
    }



public List<String> getRol() {
	return roles;
}

public void setRol(List<String> roles) {
	this.roles = roles;
}

}
