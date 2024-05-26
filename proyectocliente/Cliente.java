/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocliente;
import java.io.Serializable;

/**
 *
 * @author suyan
 */
public class Cliente implements Serializable {
    private String nombre,apellido;
    private String telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Cliente(String nom,String ape,String tel){
        this.nombre=nom;
        this.apellido=ape;
        this.telefono=tel;
    }  
    
}
