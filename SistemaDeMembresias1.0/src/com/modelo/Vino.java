package com.modelo;

/**
 * Nombre de la clase: Vino
 * Fecha: 07-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class Vino {
   private int idVino;
   private int idProducto;

    public Vino() {
    }

    public Vino(int idVino, int idProducto) {
        this.idVino = idVino;
        this.idProducto = idProducto;
    }

    public int getIdVino() {
        return idVino;
    }

    public void setIdVino(int idVino) {
        this.idVino = idVino;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
