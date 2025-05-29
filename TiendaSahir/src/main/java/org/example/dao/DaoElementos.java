package org.example.dao;

import org.example.common.ExcepcionCaracterEspecial;
import org.example.domain.Prenda;
import org.example.domain.Usuario;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface DaoElementos {

    public int verificarIdUsuario(String idUsuario);
    public int verificarIdPrenda(int idUsuario,String idPrenda);

    //Comprobar Si la lista de Usuarios y Prendas está vacia
    public boolean isEmptyElementosListUsuarios() ;
    public boolean isEmptyElementosListPrendas(String idUsuario);

    //Añadir Usuario o Prenda a la Lista
    public boolean insertarElementoUsuario(Usuario usuario);
    public boolean insertarElementoUsuario(String idUsuario, String nombre, Prendas miLista);
    public boolean insertarElementoPrenda(String idUsuario, Prenda prenda) ;
    public boolean insertarElementoPrenda(String idUsuario, String id, String prenda, String talla, LocalDate fecha,double precio);

    //Obtener la Lista de Usuarios o Prendas
    public List<Usuario> getElementosUsuarios();
    public List<Prenda> getElementosPrendas(String idUsuario);
    public List<Prenda> getElementosPrendasTalla(String idUsuario, String talla);
    public List<Prenda> listadoOrdenadoPrendas(String idUsuario,boolean ascendente);

    //Obtener factura de Usuario
    public double precioTotalFactura(Usuario usuario);
    public StringBuilder mostrarFactura(Usuario usuario);
    public StringBuilder rankingMejoresGastadores();

    //Modificar Usuario o Prenda
    public boolean modificarUsuarioNombre(String idUsuario, String nombre) throws ExcepcionCaracterEspecial;
    public boolean modificarPrendaTalla(String idUsuario,String idPrenda, String talla);
    public boolean modificarPrendaNombre(String idUsuario,String idPrenda, String prenda);

    //Eliminar Usuario o Prenda
    public void eliminarElementoUsuario(Usuario usuario);
    public boolean eliminarElementoUsuario(String idUsuarioS);
    public void eliminarElementoPrenda(String idUsuario,Prenda Elemento) ;
    public boolean eliminarElementoPrenda(String idUsuario,String idPrenda) ;

    //Fichero
    public void escribirFichero();
    public void leerFichero() throws FileNotFoundException;

    public void leerJson();
}
