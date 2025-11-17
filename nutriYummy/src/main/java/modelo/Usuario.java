/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.enums.RolUsuario;

/**
 *
 * @author rocha
 */
public class Usuario {
    private int id;
    private int numero;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private RolUsuario rol;
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(int id, int numero, String nombre, String apellidoPaterno, String apellidoMaterno, String email, RolUsuario rol, String contrasenia) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }
    
    public Usuario(int id, int numero, String nombre, String apellidoPaterno, String apellidoMaterno, String email, RolUsuario rol) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.rol = rol;
    }

    public Usuario(int numero, String nombre, String apellidoPaterno, String apellidoMaterno, String email, RolUsuario rol, String contrasenia) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    public Usuario(int numero, String nombre, String apellidoPaterno, String apellidoMaterno, String email, RolUsuario rol) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.rol = rol;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", numero=" + numero + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", email=" + email + ", rol=" + rol + ", contrasenia=" + contrasenia + '}';
    }
}
