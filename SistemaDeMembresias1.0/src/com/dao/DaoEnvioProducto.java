package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesEnvioProducto;
import com.modelo.EnvioProducto;
import java.util.List;

/**
 * Nombre de la clase: DaoEnvioProducto
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author desctroy-vasquez
 */
public class DaoEnvioProducto extends Conexion implements OperacionesEnvioProducto{

    @Override
    public List<EnvioProducto> mostrarEnvioProducto() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public void insertarProducto(EnvioProducto env) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarProducto(EnvioProducto env) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProducto(EnvioProducto env) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
