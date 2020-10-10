
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesSuscriptor;
import com.modelo.Suscriptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
         List<Suscriptor>listaSuscriptores;
        listaSuscriptores = new ArrayList();
        ResultSet res;
        
        try
        {
            this.conectar();
            String sql="select * from suscriptor";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res = pre.executeQuery();
            
            while(res.next())
            {
                Suscriptor sus = new Suscriptor();
                sus.setIdSuscriptor(res.getInt("idSuscriptor"));
                sus.setNombre(res.getString("nombre"));
                sus.setApellido(res.getString("apellido"));
                sus.setEmail(res.getString("email"));
                sus.setTelefono(res.getString("telefono"));
                sus.setDireccion(res.getString("direccion"));
                sus.setTipoSuscriptor(res.getInt("tipoSuscriptor"));
                sus.setFechaNacimiento(res.getString("fechaNacimiento"));
                sus.setTotalCompra(res.getDouble("totalCompras"));
                sus.setFecha(res.getString("fecha"));
                listaSuscriptores.add(sus);
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        
        return listaSuscriptores;
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
        finally
        {
            this.desconectar();
        }
        
    }

    @Override
    public void modificarSuscriptor(Suscriptor sus) throws Exception {
        try
       {
           this.conectar();
           String sql="update suscriptor set nombre=?, apellido=?, email=?, telefono=?, direccion=?, tipoSuscriptor=?"
                   + "fechaNacimiento=?, totalCompras=?, fecha=? where idSuscriptor=?";
           PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, sus.getNombre());
            pre.setString(2, sus.getApellido());
            pre.setString(3, sus.getEmail());
            pre.setString(4, sus.getTelefono());
            pre.setString(5, sus.getDireccion());
            pre.setInt(6, sus.getTipoSuscriptor());
            pre.setString(7, sus.getFechaNacimiento());
            pre.setDouble(8, sus.getTotalCompra());
            pre.setString(9, sus.getFecha());
            pre.setInt(10, sus.getIdSuscriptor());
            pre.executeUpdate();
           
           
       }catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error al modificar"+
                    e.getMessage());
            
           
       }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void eliminarSuscriptor(Suscriptor sus) throws Exception {
        try
        {
            this.conectar();
            String sql="delete from suscriptor where idSuscriptor=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,sus.getIdSuscriptor());
            pre.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al eliminar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    }
    }

  
    

