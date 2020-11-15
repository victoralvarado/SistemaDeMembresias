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
    public List<PersonaExterna> mostrarPersonaExterna() throws Exception;
    public void insertarPersonaExterna(PersonaExterna pe) throws Exception;
    public void modificarPersonaExterna(PersonaExterna pe) throws Exception;
    public void eliminarPersonaExterna(PersonaExterna pe) throws Exception;
}
