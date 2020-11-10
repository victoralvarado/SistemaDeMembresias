
package com.modelo;
 
    /**
 * Nombre de la clase: Oro
 * Fecha: 09-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Andrea Rosales
 */
public class Oro {

    private int idOro;
    private int idProducto;
    private String nombre;
    private double precioVenta;
    private String descripcion;

    public Oro() {
    }

    public Oro(int idOro, int idProducto, String nombre, double precioVenta, String descripcion) {
        this.idOro = idOro;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
    }

    public int getIdOro() {
        return idOro;
    }

    public void setIdOro(int idOro) {
        this.idOro = idOro;
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
