
package com.interfaces;

import com.modelo.Categoria;
import java.util.List;


/**
 * Nombre de la clase: OperacioneCategoria
 * Fecha: 09/10/2020
 * CopyRigth:
 * Version:0.1
 * @author Luna-
 */
public interface OperacionesCategoria {
    
    public List<Categoria>mostrarCategoria() throws Exception;
    public void insertarCategoria(Categoria cat) throws Exception;
    public void modificarCategoria(Categoria cat) throws Exception;
    public void eliminarCategoria(Categoria cat) throws Exception;
}
