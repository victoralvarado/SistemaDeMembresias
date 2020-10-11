package com.interfaces;

import com.modelo.Marca;
import java.util.List;

/**
 * Nombre de la interface: OperacionesMarca
 * Fecha: 9-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author andrea rosales
 */
public interface OperacionesMarca {
    public List<Marca>mostrarMarca() throws Exception;
    public void insertarMarca(Marca mar) throws Exception;
    public void modificarMarca(Marca mar) throws Exception;
    public void eliminarMarca(Marca mar) throws Exception;
    
}
