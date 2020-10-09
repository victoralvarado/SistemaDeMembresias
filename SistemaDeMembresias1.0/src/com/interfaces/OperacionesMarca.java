
package com.interfaces;

import com.modelo.Marca;
import java.util.List;

/**
 *Nombre de la clase:OperacionesMarca
 *Fecha:09/10/2020
 *CopyRigth:
 *Version:0.1
 * @author 
 */
public interface OperacionesMarca {
    
    public List<Marca>mostrarMarca() throws Exception;
    public void insertarMarca(Marca mar) throws Exception;
    public void modificarMarca(Marca mar) throws Exception;
    public void eliminarMarca(Marca mar) throws Exception;
    
}
