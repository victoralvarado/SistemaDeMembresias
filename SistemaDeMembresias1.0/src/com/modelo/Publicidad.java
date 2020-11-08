
package com.modelo;

import java.io.FileInputStream;

/**
 * Nombre de la clase: Publicidad
 * Fecha: 08/11/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author adrian luna
 */
public class Publicidad {
    private int idPublicidad;
    private FileInputStream foto;
    private String url;

    public Publicidad() {
    }

    public Publicidad(int idPublicidad, FileInputStream foto, String url) {
        this.idPublicidad = idPublicidad;
        this.foto = foto;
        this.url = url;
    }

    public int getIdPublicidad() {
        return idPublicidad;
    }

    public void setIdPublicidad(int idPublicidad) {
        this.idPublicidad = idPublicidad;
    }

    public FileInputStream getFoto() {
        return foto;
    }

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
