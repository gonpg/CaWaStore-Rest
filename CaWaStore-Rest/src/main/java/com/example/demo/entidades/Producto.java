package com.example.demo.entidades;


import javax.persistence.*;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String categoria;
    private float precio;
    private int stock;
    private String descripcion;
    private String url;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas;

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Promocion promocion;

    protected Producto() {
    }

    public Producto(String nombre, String categoria, float precio, int stock, String descripcion, String enlace_imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.url = enlace_imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace_imagen() {
        return url;
    }

    public void setEnlace_imagen(String enlace_imagen) {
        this.url = enlace_imagen;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedidos = pedido;
    }

    public void anyadirPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void eliminarPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    public List<Resena> getResena() {
        return resenas;
    }

    public void setResenas(List<Resena> resena) {
        this.resenas = resena;
    }

    public void anyadirResena(Resena resena) {
        resenas.add(resena);
    }

    public void eliminarResena(Resena resena) {
        resenas.remove(resena);
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

}
