package com.example.demo.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pedidos") 
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	
    private Date fecha;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Producto> productos;

    protected Pedido() {
    }

    public Pedido(Usuario usuario, Date fecha, List<Producto> productos) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.productos = productos;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void anyadirProduct(Producto producto) {
        this.productos.add(producto);
    }

    public void borrarProducto(Producto producto) {
        this.productos.remove(producto);
    }
    
    @Override
	public String toString() {
		return "Pedido [fecha: " + fecha + ", usuario: " + usuario.getNombreUsuario() + ", productos: " + productos + "]";
	}

}