package org.example.domain;

import org.example.dao.Usuarios;

import java.time.LocalDate;
import java.util.List;

public class Admin {
    private String contrasena;
    private Usuarios miListaUsuarios;

    public Admin(){
        contrasena = "12345";
        miListaUsuarios = new Usuarios();
    }
    public Admin(String contrasena, String id, String nombre, String idPrenda, String prenda, String talla, LocalDate fecha,double precio){
        this.contrasena = contrasena;
        this.miListaUsuarios = new Usuarios(id,nombre,idPrenda,prenda,talla,fecha,precio);
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Usuarios getMiListaUsuarios() {
        return miListaUsuarios;
    }
    public void setMiListaUsuarios(Usuarios miListaUsuarios) {
        this.miListaUsuarios = miListaUsuarios;
    }

    @Override
    public String toString() {
        return "Prenda: contrasena= " + contrasena +" miListaUsuarios= " + miListaUsuarios;
    }
}
