package com.interfaces;

import java.util.List;
import com.modelo.Publicidad;

/**
 * Nombre de la interface: OperacionesPublicidad
 * Fecha: 08-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesPublicidad {
    public List<Publicidad> mostrarPublicidad() throws Exception;
    public void insertarPublicidad(Publicidad pub) throws Exception;
    public void modificarPublicidad(Publicidad pub) throws Exception;
    public void eliminarPublicidad(Publicidad pub) throws Exception;
}
