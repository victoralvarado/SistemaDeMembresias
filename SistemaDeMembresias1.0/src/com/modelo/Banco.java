
package com.modelo;

 /**
 * Nombre de la clase: Banco
 * Fecha: 14-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Andrea Rosales
 */
public class Banco {
    
    private int idBanco;
    private String nombre;

    public Banco() {
    }

    public Banco(int idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
