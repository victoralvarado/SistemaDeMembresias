package com.interfaces;

import com.modelo.Suscriptor;
import java.util.List;

/**
 * Nombre de la interface: OperacionesSuscriptor
 * Fecha: 9-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author andrea rosales
 */
public interface OperacionesSuscriptor {
    
    public List<Suscriptor>mostrarSuscriptor() throws Exception;
    public void insertarSuscriptor(Suscriptor sus) throws Exception;
    public void modificarSuscriptor(Suscriptor sus) throws Exception;
    public void eliminarSuscriptor(Suscriptor sus) throws Exception;
    
}
