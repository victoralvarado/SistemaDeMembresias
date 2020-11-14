package com.interfaces;

import com.modelo.Producto;
import com.utilidades.CustomImageIcon;
import java.util.List;

/**
 * Nombre de la interface: OperacionesProducto
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesProducto {
    public List<Producto> mostrarProducto() throws Exception;
    public void insertarProducto(Producto p) throws Exception;
    public void modificarProducto(Producto p) throws Exception;
    public void eliminarProducto(Producto p) throws Exception;
    public CustomImageIcon getImagen(int id) throws Exception;
}
