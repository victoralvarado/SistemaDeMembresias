package com.interfaces;

import com.modelo.Usuario;
import java.util.List;

/**
 * Nombre de la interface: OperacionesUsuario
 * Fecha: 11-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public interface OperacionesUsuario {
    public List<Usuario> mostrarUsuario() throws Exception;
    public void insertarUsuario(Usuario u) throws Exception;
    public void modificarUsuario(Usuario u) throws Exception;
    public void eliminarUsuario(Usuario u) throws Exception;
    public boolean login(Usuario us) throws Exception ;
}
