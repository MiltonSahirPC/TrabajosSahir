package org.example.domain;

import org.example.dao.Prendas;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id;
    private String nombre;
    private Prendas miLista;

    public Usuario(){
        this.id = "1";
        this.nombre = "Juan";
        this.miLista = new Prendas();
    }
    public Usuario(String id, String nombre,Prendas prendas){
        this.id = id;
        this.nombre = nombre;
        this.miLista = prendas;
    }
    public Usuario(String linea) {
        String[] partes = linea.split("\\|", 3); // id|nombre|prendas
        this.id = partes[0];
        this.nombre = partes[1];

        String[] prendasRaw = partes[2].split("#");
        List<Prenda> prendas = new ArrayList<>();
        for (String prendaStr : prendasRaw) {
            prendas.add(new Prenda(prendaStr));
        }
        this.miLista = new Prendas(prendas);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Prendas getMiLista() {
        return miLista;
    }
    public void setMiLista(Prendas miLista) {
        this.miLista = miLista;
    }


    public String toStringUsuario() {
        return "Usuario: id= "+id+" nombre= "+nombre+" miLista= "+miLista.toStringLista();
    }


    public String toStringFicheroUsuario() {
        StringBuilder sb = new StringBuilder();

        sb.append(id).append("|").append(nombre).append("|");

        List<Prenda> lista = miLista.getListaElementos();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).toStringFicheroPrenda());
            if (i < lista.size() - 1) sb.append("#");
        }
        return sb.toString();
    }
}
