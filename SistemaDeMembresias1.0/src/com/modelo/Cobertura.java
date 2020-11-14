package com.modelo;

/**
* Nombre de la clase: Cobertura
* Fecha: 11-13-2020 
* CopyRigh: Ever-Alexander
* Versión: 1.0
* Autor: Alexander-Garcia
*/
public class Cobertura {
    private int idCobertura;
    private String departamento;
    private String municipio;

    public Cobertura() {
    }

    public Cobertura(int idCobertura, String departamento, String municipio) {
        this.idCobertura = idCobertura;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(int idCobertura) {
        this.idCobertura = idCobertura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}
