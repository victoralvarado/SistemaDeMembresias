
package com.interfaces;


import com.modelo.TipoSucriptor;
import java.util.List;

/**
 * Nombre de la interface: OperacionesTipoSucriptor
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesTipoSucriptor {
    
    public List<TipoSucriptor>mostrarTipoSus() throws Exception;
    public void insertarTipoSus(TipoSucriptor tSus) throws Exception;
    public void  modificarTipoSus(TipoSucriptor tSus) throws Exception;
    public void eliminarTipoSus(TipoSucriptor tSus) throws Exception;
    
}
