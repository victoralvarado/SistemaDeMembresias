package com.modelo;

/**
 * Nombre de la clase: EnvioProducto
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author desctroy-vasquez
 */

public class EnvioProducto {
    private int idEnvio;
    private int idSuscriptor;
    private int idPersonaExterna;
    private String telefono;
    private String fechaEnvio;

    public EnvioProducto() {
    }

    public EnvioProducto(int idEnvio, int idSuscriptor, int idPersonaExterna, String telefono, String fechaEnvio) {
        this.idEnvio = idEnvio;
        this.idSuscriptor = idSuscriptor;
        this.idPersonaExterna = idPersonaExterna;
        this.telefono = telefono;
        this.fechaEnvio = fechaEnvio;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdPersonaExterna() {
        return idPersonaExterna;
    }

    public void setIdPersonaExterna(int idPersonaExterna) {
        this.idPersonaExterna = idPersonaExterna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
