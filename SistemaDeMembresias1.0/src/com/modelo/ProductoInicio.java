package com.modelo;

/**
 * Nombre de la clase: ProductoInicio
 * Fecha: 01-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class ProductoInicio {
    private int codigo;
    private int idProducto;

    public ProductoInicio() {
    }

    public ProductoInicio(int codigo, int idProducto) {
        this.codigo = codigo;
        this.idProducto = idProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
