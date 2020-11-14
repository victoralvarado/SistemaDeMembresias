
package com.interfaces;

import com.modelo.Banco;
import java.util.List;

 /**
 * Nombre de la interfaz: OperacionesBanco
 * Fecha: 14-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Andrea Rosales
 */
public interface OperacionesBanco {
    
    public List<Banco> mostrarBancos() throws Exception;
    public void insertarBanco(Banco ba) throws Exception;
    public void modificarBanco(Banco ba) throws Exception;
    public void eliminarBanco(Banco ba) throws Exception;
    
}
