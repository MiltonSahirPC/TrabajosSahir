package org.example.dao;

import org.example.domain.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {

    private List<Usuario> misUsuarios = new ArrayList<>();

    public Usuarios(){
        this.misUsuarios.add(new Usuario());
    }
    public Usuarios(String id, String nombre, String idPrenda, String prenda, String talla, LocalDate fecha,double precio){

        misUsuarios.add(new Usuario(id,nombre,new Prendas(idPrenda,prenda,talla,fecha,precio)));
    }

    public List<Usuario> getMisUsuarios() {
        return misUsuarios;
    }
    public void setMisUsuarios(List<Usuario> misUsuarios) {
        this.misUsuarios = misUsuarios;
    }


    public String toStringUsuarios() {
        StringBuilder sb = new StringBuilder();
        misUsuarios.forEach(Usuario -> sb.append(Usuario.toStringUsuario()).append("\n------"));
        return sb.toString();
    }
}
