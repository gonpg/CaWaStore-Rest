package com.example.demo.entidades;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

  
    private Date fecha;

   
    private String texto;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Producto producto;

    protected Resena() {
    }

    public Resena(Producto producto, Usuario usuario, Date fecha, String texto) {
        this.producto = producto;
        this.usuario = usuario;
        this.fecha = fecha;
        this.texto = texto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String resena) {
        this.texto = resena;
    }

}
