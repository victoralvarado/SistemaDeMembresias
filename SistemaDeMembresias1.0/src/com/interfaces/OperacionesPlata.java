
package com.interfaces;

import com.modelo.Oro;
import com.modelo.Plata;
import java.util.List;


    /**
 * Nombre de la clase: OperacionesPlata
 * Fecha: 11-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Ever-Garcia
 */
public interface OperacionesPlata {
    
     public List<Plata> mostrarSuscripPlata() throws Exception;
    public void insertarSuscripPlata(Plata p) throws Exception;
    public void modificarSuscripPlata(Plata p) throws Exception;
    public void eliminarSuscripPlata(Plata p) throws Exception;
    
}
