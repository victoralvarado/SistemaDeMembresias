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
 * Nombre de la clase: DaoSuscriptor
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author andrea rosales
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
                sus.setGenero(res.getString("genero"));
                sus.setTipoSuscriptor(res.getInt("tipoSuscriptor"));
                sus.setTiempoSus(res.getString("tiempoSus"));
                sus.setCostoSus(res.getDouble("costoSus"));
                sus.setFechaNacimiento(res.getString("fechaNacimiento"));
                sus.setTotalCompra(res.getDouble("totalCompras"));
                sus.setFecha(res.getString("fecha"));
                listaSuscriptores.add(sus);
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar"+e.getMessage());
        }
        
        return listaSuscriptores;
    }

    @Override
    public void insertarSuscriptor(Suscriptor sus) throws Exception {
        try
        {
            this.conectar();
            String sql="insert into suscriptor values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, sus.getIdSuscriptor());
            pre.setString(2, sus.getNombre());
            pre.setString(3, sus.getApellido());
            pre.setString(4, sus.getEmail());
            pre.setString(5, sus.getTelefono());
            pre.setString(6, sus.getDireccion());
            pre.setString(7, sus.getGenero());
            pre.setInt(8, sus.getTipoSuscriptor());
            pre.setString(9, sus.getTiempoSus());
            pre.setDouble(10, sus.getCostoSus());
            pre.setString(11, sus.getFechaNacimiento());
            pre.setDouble(12, sus.getTotalCompra());
            pre.setString(13, sus.getFecha());
            pre.executeUpdate();
           
        }catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null, "Error al insertar"+
                    e.getMessage());
           
        }
    }

    @Override
    public void modificarSuscriptor(Suscriptor sus) throws Exception {
        try
       {
           this.conectar();
           String sql="update suscriptor set nombre=?, apellido=?, email=?, telefono=?, direccion=?, genero =?, tipoSuscriptor=?,tiempoSus =?,costoSus=?, fechaNacimiento=?, totalCompras=?, fecha=? where idSuscriptor=?";
           PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, sus.getNombre());
            pre.setString(2, sus.getApellido());
            pre.setString(3, sus.getEmail());
            pre.setString(4, sus.getTelefono());
            pre.setString(5, sus.getDireccion());
            pre.setString(6, sus.getGenero());
            pre.setInt(7, sus.getTipoSuscriptor());
            pre.setString(8, sus.getTiempoSus());
            pre.setDouble(9, sus.getCostoSus());
            pre.setString(10, sus.getFechaNacimiento());
            pre.setDouble(11, sus.getTotalCompra());
            pre.setString(12, sus.getFecha());
            pre.setInt(13, sus.getIdSuscriptor());
            pre.executeUpdate();
           
           
       }catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error al modificar en la base"+
                    e.getMessage());
            
           
       }
    }

    @Override
    public void eliminarSuscriptor(Suscriptor sus) throws Exception {
        try {
            this.conectar();
            String sql = "delete from suscriptor where idSuscriptor=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, sus.getIdSuscriptor());
            pre.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar"
                    + e.getMessage());
        } finally {
            this.desconectar();
        }
    }
}

  
    

