package org.example.service;

import org.example.common.ExcepcionCaracterEspecial;
import org.example.dao.DaoElementos;
import org.example.dao.DaoElementosImplementacion;
import org.example.dao.Prendas;
import org.example.domain.Prenda;
import org.example.domain.Usuario;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class GestionElementosImplementacion implements GestionElementos{
    private DaoElementos daoElementos;

    public GestionElementosImplementacion(){
        daoElementos = new DaoElementosImplementacion();
    }

    public GestionElementosImplementacion(DaoElementos gestionElementos){
        this.daoElementos = gestionElementos;
    }

    public int verificarIdUsuario(String idUsuario){
        return daoElementos.verificarIdUsuario(idUsuario);
    }
    public int verificarIdPrenda(int idUsuario,String idPrenda){
        return daoElementos.verificarIdPrenda(idUsuario,idPrenda);
    }
    //Comprobar Si la lista de Usuarios y Prendas está vacia
    @Override
    public boolean isEmptyElementosListUsuarios() {
        return daoElementos.isEmptyElementosListUsuarios();
    }
    @Override
    public boolean isEmptyElementosListPrendas(String idUsuarios) {
        return daoElementos.isEmptyElementosListPrendas(idUsuarios);
    }

    //Añadir Usuario o Prenda a la Lista
    @Override
    public boolean insertarElementoUsuario(Usuario usuario){
        return daoElementos.insertarElementoUsuario(usuario);
    }
    @Override
    public boolean insertarElementoUsuario(String idUsuario, String nombre, Prendas miLista){
        return daoElementos.insertarElementoUsuario(idUsuario, nombre, miLista);
    }
    @Override
    public boolean insertarElementoPrenda(String idUsuario, Prenda prenda) {
        return daoElementos.insertarElementoPrenda(idUsuario, prenda);
    }

    @Override
    public boolean insertarElementoPrenda(String idUsuario,String id, String prenda, String talla, LocalDate fecha,double precio) {
        return daoElementos.insertarElementoPrenda(idUsuario, id,prenda,talla,fecha,precio);
    }

    //Obtener la Lista de Usuarios o Prendas
    @Override
    public List<Usuario> getElementosUsuarios(){
        return daoElementos.getElementosUsuarios();
    }
    @Override
    public List<Prenda> getElementosPrendas(String idUsuario) {
        return daoElementos.getElementosPrendas(idUsuario);
    }

    @Override
    public List<Prenda> getElementosPrendasTalla(String idUsuario, String talla) {
        return daoElementos.getElementosPrendasTalla(idUsuario, talla);
    }
    @Override
    public List<Prenda> listadoOrdenadoPrendas(String idUsuario,boolean ascendente) {
        return daoElementos.listadoOrdenadoPrendas(idUsuario, ascendente);
    }

    //Mostrar Factura
    public double precioTotalFactura(Usuario usuario){
        return daoElementos.precioTotalFactura(usuario);
    }
    public StringBuilder mostrarFactura(Usuario usuario){
        return daoElementos.mostrarFactura(usuario);
    }
    public StringBuilder rankingMejoresGastadores(){
        return daoElementos.rankingMejoresGastadores();
    }

    //Modificar Usuario o Prenda
    @Override
    public boolean modificarUsuarioNombre(String idUsuario, String nombre) throws ExcepcionCaracterEspecial {
        return daoElementos.modificarUsuarioNombre(idUsuario, nombre);
    }
    @Override
    public boolean modificarPrendaTalla(String idUsuario,String idPrenda, String talla) {
        return daoElementos.modificarPrendaTalla(idUsuario,idPrenda,talla);
    }
    @Override
    public boolean modificarPrendaNombre(String idUsuario,String idPrenda, String prenda) {
        return daoElementos.modificarPrendaNombre(idUsuario,idPrenda, prenda);
    }

    //Eliminar Usuario o Prenda
    @Override
    public void eliminarElementoUsuario(Usuario usuario){
        daoElementos.eliminarElementoUsuario(usuario);
    }
    @Override
    public boolean eliminarElementoUsuario(String idUsuarioS){
        return daoElementos.eliminarElementoUsuario(idUsuarioS);
    }
    @Override
    public void eliminarElementoPrenda(String idUsuario,Prenda prenda){
        daoElementos.eliminarElementoPrenda(idUsuario,prenda);
    }
    @Override
    public boolean eliminarElementoPrenda(String idUsuario,String idPrenda){
        return daoElementos.eliminarElementoPrenda(idUsuario,idPrenda);
    }

    @Override
    public void escribirFichero(){
        daoElementos.escribirFichero();
    }
    @Override
    public void leerFichero() throws FileNotFoundException{
        daoElementos.leerFichero();
    }

    @Override
    public void leerJson(){
        daoElementos.leerJson();
    }
}
