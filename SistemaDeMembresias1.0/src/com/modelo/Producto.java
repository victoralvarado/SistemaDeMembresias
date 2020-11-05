package com.modelo;

import java.io.FileInputStream;

/**
 * Nombre de la clase: Producto
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class Producto {
    private int idProducto;
    private int idCategoria;
    private int idMarca;
    private String tipo;
    private String nombre;
    private String descripcion;
    private FileInputStream imagen;
    private int stock;
    private double precioVenta;
    private String fecha;

    public Producto() {
    }

    public Producto(int idProducto, int idCategoria, int idMarca, String tipo, String nombre, String descripcion, FileInputStream imagen, int stock, double precioVenta, String fecha) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
}
