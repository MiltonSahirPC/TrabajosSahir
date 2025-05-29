package org.example.dao;

import org.example.domain.Prenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prendas {

    private List<Prenda> miLista = new ArrayList<>();

    public Prendas(){
        this.miLista.add(new Prenda());
    }
    public Prendas(String id, String prenda, String talla, LocalDate fecha,double precio){
        miLista.add(new Prenda(id, prenda, talla,fecha, precio));
    }
    public Prendas(Prenda prenda){
        miLista.add(prenda);
    }

    public Prendas(List<Prenda> lista){
        miLista = lista;
    }
    public List<Prenda> getListaElementos(){
        return miLista;
    }
    public void setMiLista(List<Prenda> miLista) {
        this.miLista = miLista;
    }


    public String toStringLista() {
        StringBuilder sb = new StringBuilder();
        miLista.forEach(Prenda -> sb.append(Prenda.toStringPrenda()).append("\n"));
        return "MiLista=" + sb;
    }
    public String toStringFicheroLista() {
        StringBuilder sb = new StringBuilder();
        miLista.forEach(Prenda -> sb.append(Prenda.toStringFicheroPrenda()));
        return sb.toString();
    }
}
