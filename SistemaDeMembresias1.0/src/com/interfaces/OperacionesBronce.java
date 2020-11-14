package com.interfaces;

import com.modelo.Bronce;
import java.util.List;

/**
* Nombre de la interface: OperacionesBronce
* Fecha: 11-12-2020 
* CopyRigh: ROKE
* Versión: 1.0
* Autor: Aguillon, Alvarado, Luna, Rosales y Vasquez
*/
public interface OperacionesBronce {
    public List<Bronce> mostrarSuscipcionBronce() throws Exception;
    public void insertarSuscipcionBronce(Bronce b) throws Exception;
    public void modificarSuscipcionBronce(Bronce b) throws Exception;
    public void eliminarSuscipcionBronce(Bronce b) throws Exception;
    public Bronce getIdProducto(int idBronce);

}
