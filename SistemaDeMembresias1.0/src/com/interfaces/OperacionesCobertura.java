package com.interfaces;

import com.modelo.Cobertura;
import java.util.List;

/**
* Nombre de la clase: OperacionesCobertura
* Fecha: 11-13-2020 
* CopyRigh: Ever-Alexander
* Versión: 1.0
* Autor: Alexander-Garcia
*/
public interface OperacionesCobertura {
    public List<Cobertura> mostrarCobertura() throws Exception;
    public void insertarCobertura(Cobertura cob) throws Exception;
    public void modificarCobertura(Cobertura cob) throws Exception;
    public void eliminarCobertura(Cobertura cob) throws Exception;
}
