package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCarrito;
import com.modelo.Carrito;
import java.util.List;

/**
 * Nombre de la clase: DaoCarrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author ever vasquez
 */
public class DaoCarrito extends Conexion implements OperacionesCarrito{

    @Override
    public List<Carrito> mostrarCarrito() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarCarrito(Carrito car) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarCarrito(Carrito car) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCarrito(Carrito car) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
