
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesSuscriptor;
import com.modelo.Suscriptor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *Nombre de la clase:DaoSuscriptor
 *Fecha:09/10/2020
 *CopyRigth:
 *Version:0.1
 * @author 
 */
public class DaoSuscriptor extends Conexion implements OperacionesSuscriptor {

    @Override
    public List<Suscriptor> mostrarSuscriptor() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarSuscriptor(Suscriptor sus) throws Exception {
       
        try
        {
            this.conectar();
            String sql="insert into suscriptor values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, sus.getIdSuscriptor());
            pre.setString(2, sus.getNombre());
            pre.setString(3, sus.getApellido());
            pre.setString(4, sus.getEmail());
            pre.setString(5, sus.getTelefono());
            pre.setString(6, sus.getDireccion());
            pre.setInt(7, sus.getTipoSuscriptor());
            pre.setString(8, sus.getFechaNacimiento());
            pre.setDouble(9, sus.getTotalCompra());
            pre.setString(10, sus.getFecha());
            pre.executeUpdate();
            
        }catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null, "Error al insertar"+
                    e.getMessage());
            
        }
        
    }

    @Override
    public void modificarSuscriptor(Suscriptor sus) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarSuscriptor(Suscriptor sus) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
