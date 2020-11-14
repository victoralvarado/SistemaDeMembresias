package com.interfaces;

import com.modelo.OrdenDetalle;
import java.util.List;

/**
* Nombre de la clase: OperacionesOrdenDetalle
 Fecha: 10-09-2020 
 CopyRigh: ITCA-FEPADE
 Versión: 1.0
 Autor: Aguillon, Alvarado, Luna, Rosales y Vasquez
*/
public interface OperacionesOrdenDetalle {
    public List<OrdenDetalle>mostrarDetalle() throws Exception;
    public void insertarDetalle(OrdenDetalle od) throws Exception;
    public void modificarDetalle(OrdenDetalle od) throws Exception;
    public void eliminarDetalle(OrdenDetalle od) throws Exception;

}
