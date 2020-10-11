package com.interfaces;

import com.modelo.Carrito;
import java.util.List;

/**
 * Nombre de la interface: OperacionesCarrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author ever vasquez
 */
public interface OperacionesCarrito {
    public List<Carrito> mostrarCarrito() throws Exception;
    public void insertarCarrito(Carrito car) throws Exception;
    public void modificarCarrito(Carrito car) throws Exception;
    public void eliminarCarrito(Carrito car) throws Exception;
}
