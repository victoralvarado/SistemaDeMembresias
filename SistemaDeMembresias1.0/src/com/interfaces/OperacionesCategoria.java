package com.interfaces;

import com.modelo.Categoria;
import java.util.List;


/**
 * Nombre de la interface: OperacionesCategoria
 * Fecha: 9-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesCategoria {
    public List<Categoria>mostrarCategoria() throws Exception;
    public void insertarCategoria(Categoria cat) throws Exception;
    public void modificarCategoria(Categoria cat) throws Exception;
    public void eliminarCategoria(Categoria cat) throws Exception;
}
