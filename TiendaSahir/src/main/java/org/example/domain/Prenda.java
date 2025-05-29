package org.example.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Prenda {

    private String id;
    private String prenda;
    private String talla;
    private LocalDate fecha;
    private double precio;



    public Prenda(){
        this.id = "1";
        this.prenda = "Polo";
        this.talla = "M";
        this.fecha = LocalDate.now();
        this.precio = 30;
    }
    public Prenda(String id, String prenda, String talla,LocalDate fecha, double precio){
        this.id = id;
        this.prenda = prenda;
        this.talla = talla;
        this.fecha = fecha;
        this.precio = precio;
    }
    public Prenda(String linea){
        String[] datos = linea.split(";");
        this.id = datos[0];
        this.prenda = datos[1];
        this.talla = datos[2];
        this.fecha= LocalDate.parse(datos[3]);
        this.precio = Double.parseDouble(datos[4]);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPrenda() {
        return prenda;
    }
    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }
    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public LocalDate getMiFecha() {
        return fecha;
    }
    public void setMiFecha(LocalDate miFecha) {
        this.fecha = miFecha;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio=precio;
    }


    public String toStringPrenda() {
        return "Prenda: id= " + id + " prenda= " + prenda + " talla= " + talla+" Fecha= "+fecha+" Precio= "+String.format(Locale.US,"%.2f", precio);
    }

    public String toStringFicheroPrenda() {
        return id+";"+prenda+";"+talla+";"+fecha+";"+String.format(Locale.US,"%.2f", precio);
    }
}
