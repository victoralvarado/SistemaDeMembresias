package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCategoria;
import com.modelo.Categoria;
import java.util.List;

/**
 * Nombre de la clase: DaoCategoria
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author adrian luna
 */
public class DaoCategoria extends Conexion implements OperacionesCategoria{

    @Override
    public List<Categoria> mostrarCategoria() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarCategoria(Categoria cat) throws Exception {
        
    }

    @Override
    public void modificarCategoria(Categoria cat) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCategoria(Categoria cat) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
