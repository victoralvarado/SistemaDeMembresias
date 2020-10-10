package com.modelo;

/**
 * Nombre de la clase: Carrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author desctroy-vasquez
 */

public class Carrito {
    private int idCarrito;
    private int idSuscriptor;
    private int idProducto;
    private int cantidad;

    public Carrito() {
    }

    public Carrito(int idCarrito, int idSuscriptor, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idSuscriptor = idSuscriptor;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
