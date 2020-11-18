package com.modelo;

/**
 * Nombre de la clase: EnvioProducto
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author ever vasquez
 */
public class EnvioProducto {
    private int idEnvio;
    private int idSuscriptor;
    private int idPersonaExterna;
    private String fechaEnvio;
    private int idProducto;
    private String detalleEnvio;
    private int estado;
    private int idCobertura;

    public EnvioProducto() {
    }

    public EnvioProducto(int idEnvio, int idSuscriptor, int idPersonaExterna, String fechaEnvio, int idProducto, String detalleEnvio, int estado, int idCobertura) {
        this.idEnvio = idEnvio;
        this.idSuscriptor = idSuscriptor;
        this.idPersonaExterna = idPersonaExterna;
        this.fechaEnvio = fechaEnvio;
        this.idProducto = idProducto;
        this.detalleEnvio = detalleEnvio;
        this.estado = estado;
        this.idCobertura = idCobertura;
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

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDetalleEnvio() {
        return detalleEnvio;
    }

    public void setDetalleEnvio(String detalleEnvio) {
        this.detalleEnvio = detalleEnvio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(int idCobertura) {
        this.idCobertura = idCobertura;
    }

    
    
}
