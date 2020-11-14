package com.interfaces;
import com.modelo.Venta;
import java.util.List;

/**
* Nombre de la clase: OperacionesVenta
* Fecha: 10-09-2020 
* CopyRigh: ITCA-FEPADE
* Versión: 1.0
* Autor: Aguillon, Alvarado, Luna, Rosales y Vasquez
*/
public interface OperacionesVenta {

    public List<Venta>mostrarVenta() throws Exception;
    public void insertarVenta(Venta vent) throws Exception;
    public void modificarVenta(Venta vent) throws Exception;
    public void eliminarVenta(Venta vent) throws Exception;
}
