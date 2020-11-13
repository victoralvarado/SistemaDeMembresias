
package com.modelo;
 
    /**
 * Nombre de la clase: Plata
 * Fecha: 12-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Ever-Garcia
 */
public class Plata {

    private int idPlata;
    private int idProducto;
    private String nombre;
    private double precioVenta;
    private String descripcion;

    public Plata() {
    }

    public Plata(int idPlata, int idProducto, String nombre, double precioVenta, String descripcion) {
        this.idPlata = idPlata;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
    }

    public int getIdPlata() {
        return idPlata;
    }

    public void setIdPlata(int idPlata) {
        this.idPlata = idPlata;
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