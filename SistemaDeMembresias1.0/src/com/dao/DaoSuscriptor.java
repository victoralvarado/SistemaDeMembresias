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
                sus.setTiempoSus(res.getString("tiempoSuscripcion"));
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
            String sql="insert into suscriptor(nombre, apellido, email, telefono, direccion, "
                    + "tipoSuscriptor, fechaNacimiento, totalCompras, fecha, genero, tiempoSuscripcion) values "
                    + "(?,?,?,?,?,?,?,?,?,?,?)";
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
            pre.setString(10, sus.getGenero());
            pre.setString(11, sus.getTiempoSus());
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
           String sql="update suscriptor set nombre=?, apellido=?, email=?, telefono=?, direccion=?, tipoSuscriptor=?, fechaNacimiento=?, totalCompras=?, fecha=?, genero, tiempoSuscripcion =? where idSuscriptor=?";
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
            pre.setString(10, sus.getGenero());
            pre.setString(11, sus.getTiempoSus());
            pre.setInt(12, sus.getIdSuscriptor());
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
    
    public int getIdSuscriptor(String email) {
        int id = 0;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "Select idSuscriptor from suscriptor where email = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, email);
            rs = pre.executeQuery();
            while (rs.next()) {                
                id = rs.getInt("idSuscriptor");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error idSuscriptor"
                    + e.getMessage());
        }
        return id;
    }
    
    public int tipoSuscriptor(int idSuscriptor){
        int tipos = 0;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "Select tipoSuscriptor from suscriptor where idSuscriptor = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idSuscriptor);
            rs = pre.executeQuery();
            while (rs.next()) {                
                tipos = rs.getInt("tipoSuscriptor");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tiposuscriptor"
                    + e.getMessage());
        }
        return tipos;
    }
}

  
    

