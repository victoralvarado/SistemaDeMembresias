package com.modelo;

import java.io.FileInputStream;

/**
 * Nombre de la clase: Portada
 * Fecha: 04-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class Portada {
    private int codigo;
    private FileInputStream imagen;

    public Portada() {
    }

    public Portada(int codigo, FileInputStream imagen) {
        this.codigo = codigo;
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen) {
        this.imagen = imagen;
    }
    
}
