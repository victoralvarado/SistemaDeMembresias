package com.modelo;

/**
* Nombre de la clase: OrdenDetalle
* Fecha: 10-09-2020 
* CopyRigh: ITCA-FEPADE
* Versión: 1.0
* Autor: Roberto Aguillón
*/

public class OrdenDetalle {

    private int idDetalle;
    private int cantidad;
    private float precio;

    public OrdenDetalle() {
    }

    
    public OrdenDetalle(int idDetalle, int cantidad, float precio) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
