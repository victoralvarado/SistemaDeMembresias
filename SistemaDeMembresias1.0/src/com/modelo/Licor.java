package com.modelo;

/**
 * Nombre de la clase: Licor
 * Fecha: 07-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class Licor {
    private int idLicor;
    private int idProducto;

    public Licor() {
    }

    public Licor(int idLicor, int idProducto) {
        this.idLicor = idLicor;
        this.idProducto = idProducto;
    }

    public int getIdLicor() {
        return idLicor;
    }

    public void setIdLicor(int idLicor) {
        this.idLicor = idLicor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
