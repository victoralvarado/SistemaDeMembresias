package com.interfaces;

import com.modelo.Carrito;
import java.util.List;

/**
 * Nombre de la clase: OperacionesCarrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author desctroy-vasquez
 */
public interface OperacionesCarrito {
    public List<Carrito> mostrarCarrito() throws Exception;
    public void insertarCarrito(Carrito car) throws Exception;
    public void modificarCarrito(Carrito car) throws Exception;
    public void eliminarCarrito(Carrito car) throws Exception;
}
