
package com.interfaces;

import com.modelo.Orden;
import java.util.List;

/**
 * Nombre de la clase: OpreacionesOrden
 * Fecha: 19/10/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public interface OperacionesOrden {
    public List<Orden>mostrarOrden() throws Exception;
    public void insertarOrden(Orden ord) throws Exception;
    public void modificarOrden(Orden ord) throws Exception;
    public void eliminarOrden(Orden ord) throws Exception;
}
