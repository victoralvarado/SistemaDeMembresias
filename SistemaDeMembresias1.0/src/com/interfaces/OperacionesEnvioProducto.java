package com.interfaces;

import com.modelo.EnvioProducto;
import java.util.List;

/**
 * Nombre de la interface: OperacionesEnvioProducto
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesEnvioProducto {
    public List<EnvioProducto> mostrarEnvioProducto() throws Exception;
    public void insertarProducto(EnvioProducto env) throws Exception;
    public void modificarProducto(EnvioProducto env) throws Exception;
    public void eliminarProducto(EnvioProducto env) throws Exception;
}
