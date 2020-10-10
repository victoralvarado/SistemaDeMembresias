
package com.modelo;

/**
 * nombre de la clase: Categoria
 * Fecha: 09/10/2020
 * CopyRigth:
 * Version:0.1
 * @author Luna-
 */
public class Categoria {
    private int idCategoria;
    private String categoria;

    public Categoria() {
    }

    public Categoria(int idCategoria, String categoria) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
