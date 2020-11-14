package com.interfaces;

import com.modelo.PersonaExterna;
import java.util.List;

/**
 * Nombre de la interface: OperacionesPersonaExterna
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public interface OperacionesPersonaExterna {
    public List<PersonaExterna> mostrarProducto() throws Exception;
    public void insertarProducto(PersonaExterna pe) throws Exception;
    public void modificarProducto(PersonaExterna pe) throws Exception;
    public void eliminarProducto(PersonaExterna pe) throws Exception;
}
