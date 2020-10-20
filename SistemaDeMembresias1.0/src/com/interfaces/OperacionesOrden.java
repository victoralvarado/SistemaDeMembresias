
package com.interfaces;

import com.modelo.Orden;
import java.util.List;

/**
 *
 * @author Luna-
 */
public interface OperacionesOrden {
    public List<Orden>mostrarOrden() throws Exception;
    public void insertarOrden(Orden ord) throws Exception;
    public void modificarOrden(Orden ord) throws Exception;
    public void eliminarOrden(Orden ord) throws Exception;
}
