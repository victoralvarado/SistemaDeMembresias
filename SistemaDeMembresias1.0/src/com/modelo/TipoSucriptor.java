
package com.modelo;

/**
 *
 * @author manza
 */
public class TipoSucriptor {
    
    private int tipoSuscriptor;
    private String nombre;
    private double costo;
    private String detalle;

    public TipoSucriptor() {
    }

    public TipoSucriptor(int tipoSuscriptor, String nombre, double costo, String detalle) {
        this.tipoSuscriptor = tipoSuscriptor;
        this.nombre = nombre;
        this.costo = costo;
        this.detalle = detalle;
    }

    public int getTipoSuscriptor() {
        return tipoSuscriptor;
    }

    public void setTipoSuscriptor(int tipoSuscriptor) {
        this.tipoSuscriptor = tipoSuscriptor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
