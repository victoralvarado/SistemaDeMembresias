package com.modelo;

/**
* Nombre de la clase: Bronce
* Fecha: 11-12-2020 
* CopyRigh: ROKE
* Versión: 1.0
* Autor: Roberto Aguillón
*/
public class Bronce {
    
    private int idBronce;
    private int idProducto;
    private String nombre;
    private double precioVenta;
    private String descripcion;

    public Bronce() {
    }

    public Bronce(int idBronce, int idProducto, String nombre, double precioVenta, String descripcion) {
        this.idBronce = idBronce;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
    }

    public int getIdBronce() {
        return idBronce;
    }

    public void setIdBronce(int idBronce) {
        this.idBronce = idBronce;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

}
