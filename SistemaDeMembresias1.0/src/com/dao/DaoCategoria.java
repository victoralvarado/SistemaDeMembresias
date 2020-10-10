
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCategoria;
import com.modelo.Categoria;
import java.util.List;

/**
 *
 * @author Luna-
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
