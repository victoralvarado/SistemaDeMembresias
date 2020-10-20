
package com.modelo;

/**
 * Nombre de la clase: Categoria
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public class Orden {
    private int idOrden;
    private int idSuscriptor;
    private int idDetalle;
    private float total;
    private int idEnvio;

    public Orden() {
    }

    public Orden(int idOrden, int idSuscriptor, int idDetalle, float total, int idEnvio) {
        this.idOrden = idOrden;
        this.idSuscriptor = idSuscriptor;
        this.idDetalle = idDetalle;
        this.total = total;
        this.idEnvio = idEnvio;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }
    
    
}
