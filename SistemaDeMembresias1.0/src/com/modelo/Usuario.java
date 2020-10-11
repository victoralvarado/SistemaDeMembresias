package com.modelo;

import java.io.FileInputStream;

/**
 * Nombre de la clase: Usuario
 * Fecha: 11-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class Usuario {
    private int codigoUsuario;
    private String email;
    private String nombre;
    private String apellido;
    private int tipoUsuario;
    private String password;
    private int estado;
    private FileInputStream foto;
    private String ultimoLogin;
    private String fecha;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String email, String nombre, String apellido, int tipoUsuario, String password, int estado, FileInputStream foto, String ultimoLogin, String fecha) {
        this.codigoUsuario = codigoUsuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
        this.estado = estado;
        this.foto = foto;
        this.ultimoLogin = ultimoLogin;
        this.fecha = fecha;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public FileInputStream getFoto() {
        return foto;
    }

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }

    public String getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(String ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
