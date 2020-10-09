/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces;

import com.modelo.Suscriptor;
import java.util.List;

/**
 *Nombre de la clase: OperacionesSuscriptor
 *Fecha: 09/10/2020
 *CopyRigth:
 *Version:0.1
 * @author
 */
public interface OperacionesSuscriptor {
    
    public List<Suscriptor>mostrarSuscriptor() throws Exception;
    public void insertarSuscriptor(Suscriptor sus) throws Exception;
    public void modificarSuscriptor(Suscriptor sus) throws Exception;
    public void eliminarSuscriptor(Suscriptor sus) throws Exception;
    
}
