
package com.interfaces;


import com.modelo.TipoSucriptor;
import java.util.List;

/**
 *
 * @author manza
 */
public interface OperacionesTipoSucriptor {
    
    public List<TipoSucriptor>mostrarTipoSus() throws Exception;
    public void insertarTipoSus(TipoSucriptor tSus) throws Exception;
    public void  modificarTipoSus(TipoSucriptor tSus) throws Exception;
    public void eliminarTipoSus(TipoSucriptor tSus) throws Exception;
    
}
