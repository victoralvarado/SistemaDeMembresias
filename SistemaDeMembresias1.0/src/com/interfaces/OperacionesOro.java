
package com.interfaces;

import com.modelo.Oro;
import java.util.List;


    /**
 * Nombre de la clase: Operaciones oro
 * Fecha: 09-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesOro {
    
     public List<Oro> mostrarSusOro() throws Exception;
    public void insertarSusOro(Oro p) throws Exception;
    public void modificarSusOro(Oro p) throws Exception;
    public void eliminarSusOro(Oro p) throws Exception;
    
}
