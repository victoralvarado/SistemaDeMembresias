package com.modelo;

/**
 * Nombre de la clase: PersonaExterna
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class PersonaExterna {
    private int idPersonaExterna;
    private String nombre;
    private String dui;
    private String telefonoMovil;
    private int idSuscriptor;

    public PersonaExterna() {
    }

    public PersonaExterna(int idPersonaExterna, String nombre, String dui, String telefonoMovil, int idSuscriptor) {
        this.idPersonaExterna = idPersonaExterna;
        this.nombre = nombre;
        this.dui = dui;
        this.telefonoMovil = telefonoMovil;
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdPersonaExterna() {
        return idPersonaExterna;
    }

    public void setIdPersonaExterna(int idPersonaExterna) {
        this.idPersonaExterna = idPersonaExterna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    
    
}
