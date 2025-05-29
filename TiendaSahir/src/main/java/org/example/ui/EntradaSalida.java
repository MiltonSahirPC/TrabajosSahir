package org.example.ui;

import org.example.common.Constantes;
import org.example.common.ExcepcionCaracterEspecial;
import org.example.dao.GestionFicheros;
import org.example.dao.GestionJson;
import org.example.dao.Prendas;
import org.example.domain.Admin;
import org.example.domain.Prenda;
import org.example.domain.Usuario;
import org.example.service.GestionElementos;
import org.example.service.GestionElementosImplementacion;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EntradaSalida {
    private static GestionElementos servicio;

    public EntradaSalida(GestionElementos ge) {
        servicio = ge;
    }

    public EntradaSalida() {
        servicio= new GestionElementosImplementacion();
    }


    //Comprobar Si la lista de Usuarios y Prendas está vacia
    public boolean isEmptyElementosListUsuarios(){
        boolean respuesta = servicio.isEmptyElementosListUsuarios();

        if (respuesta){
            System.out.println(Constantes.LISTA_VACIA);
        }else {
            System.out.println(Constantes.LISTA_NO_VACIA);
        }

        return respuesta;
    }
    public boolean isEmptyElementosListPrendas(String idUsuario){
        boolean respuesta = servicio.isEmptyElementosListPrendas(idUsuario);

        if (respuesta){
            System.out.println(Constantes.LISTA_VACIA);
        }else {
            System.out.println(Constantes.LISTA_NO_VACIA);
        }

        return respuesta;
    }

    //Añadir Usuario o Prenda a la Lista
    public boolean insertarElementoUsuario(Usuario usuario){
        boolean respuesta = servicio.insertarElementoUsuario(usuario);
        if (respuesta){
            System.out.println(Constantes.USUARIO_ANADIDO);
        }else {
            System.out.println(Constantes.USUARIO_NO_ANADIDO);
        }
        return respuesta;
    }
    public boolean insertarElementoUsuario(String idUsuario, String nombre, Prendas miLista){

        boolean respuesta = servicio.insertarElementoUsuario(idUsuario, nombre, miLista);

        if (respuesta){
            System.out.println(Constantes.USUARIO_ANADIDO);
        }else {
            System.out.println(Constantes.USUARIO_NO_ANADIDO);
        }
        return respuesta;
    }
    public boolean insertarElementoPrenda(String idUsuario, Prenda prenda){
        boolean respuesta = servicio.insertarElementoPrenda(idUsuario, prenda);
        if (respuesta){
            System.out.println(Constantes.PRENDA_ANADIDO);
        }else {
            System.out.println(Constantes.PRENDA_NO_ANADIDO);
        }
        return respuesta;
    }
    public boolean insertarElementoPrenda(String idUsuario, String id, String prenda, String talla, LocalDate fecha,double precio){
        boolean respuesta = servicio.insertarElementoPrenda(idUsuario,id,prenda,talla,fecha,precio);

        if (respuesta){
            System.out.println(Constantes.PRENDA_ANADIDO);
        }else {
            System.out.println(Constantes.PRENDA_NO_ANADIDO);
        }
        return respuesta;
    }

    //Obtener la Lista de Usuarios o Prendas
    public List<Usuario> getElementosUsuarios(){
        return servicio.getElementosUsuarios();
    }
    public List<Prenda> getElementosPrendas(String idUsuario){
        return servicio.getElementosPrendas(idUsuario);
    }
    public List<Prenda> getElementosPrendasTalla(String idUsuario, String talla){
        return servicio.getElementosPrendasTalla(idUsuario, talla);
    }
    public List<Prenda> listadoOrdenadoPrendas(String idUsuario,boolean ascendente){
        return servicio.listadoOrdenadoPrendas(idUsuario, ascendente);
    }

    //Mostrar Factura
    public double precioTotalFactura(Usuario usuario){
        return servicio.precioTotalFactura(usuario);
    }
    public StringBuilder mostrarFactura(Usuario usuario){
        return servicio.mostrarFactura(usuario);
    }
    public StringBuilder rankingMejoresGastadores(){
        return servicio.rankingMejoresGastadores();
    }

    //Modificar Elemento
    public boolean modificarUsuarioNombre(String idUsuario, String nombre) throws ExcepcionCaracterEspecial{
        return servicio.modificarUsuarioNombre(idUsuario, nombre);
    }
    public boolean modificarPrendaTalla(String idUsuario,String idPrenda, String talla){
        return servicio.modificarPrendaTalla(idUsuario, idPrenda, talla);
    }
    public boolean modificarPrendaNombre(String idUsuario,String idPrenda, String prenda){
        return servicio.modificarPrendaNombre(idUsuario, idPrenda, prenda);
    }

    //Eliminar Usuario o Prenda
    public void eliminarElementoUsuario(Usuario usuario){
        servicio.eliminarElementoUsuario(usuario);
    }
    public boolean eliminarElementoUsuario(String idUsuarioS){
        boolean respuesta = servicio.eliminarElementoUsuario(idUsuarioS);
        if (respuesta){
            System.out.println(Constantes.USUARIO_ELIMINADO);
        }else {
            System.out.println(Constantes.USUARIO_NO_ELIMINADO);
        }
        return respuesta;
    }
    public void eliminarElementoPrenda(String idUsuario,Prenda Elemento){
        servicio.eliminarElementoPrenda(idUsuario, Elemento);
    }
    public boolean eliminarElementoPrenda(String idUsuario,String idPrenda){
        boolean respuesta = servicio.eliminarElementoPrenda(idUsuario, idPrenda);
        if (respuesta){
            System.out.println(Constantes.PRENDA_ELIMINADO);
        }else {
            System.out.println(Constantes.PRENDA_NO_ELIMINADO);
        }
        return respuesta;
    }

    public void escribirFichero(){
        servicio.escribirFichero();
    }
    public void leerFichero() throws FileNotFoundException{
        servicio.leerFichero();
    }
    public void leerJson(){
        servicio.leerJson();
    }

    public void menuPrincipal() throws ExcepcionCaracterEspecial, FileNotFoundException {
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.BIENVENIDO);
        int menu;
        do {
            System.out.println(Constantes.OPCIONES_MENU_PRINCIPAL);

            menu = lector.nextInt();

            switch (menu){
                case 1:{
                    opcioneCaseVacio();//Finish
                }break;
                case 2:{
                    opcionCaseAnadir();//Finish
                }break;
                case 3:{
                    opcionCaseListas();//Finish
                }break;
                case 4:{
                    opcionCaseFactura();//Finish
                }break;
                case 5:{//Finish
                    opcionCaseModificar();//Finish
                }break;
                case 6:{
                    opcionCaseEliminar();//Finish
                }break;
                case 7:{
                    opcionCaseFicheros();//Finish
                }break;
                case 8:{
                    opcionCaseJson();//Finish
                }

                case 0:{
                    System.out.println(Constantes.OPCION_MENU_0);
                }break;
                default:{
                    System.out.println(Constantes.OPCION_DEFAULT);
                }
            }
        }while (menu!=0);
    }
    public void opcioneCaseVacio(){//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE1);
        int opcionCase1 = lector.nextInt();
        switch (opcionCase1){
            case 1:{
                isEmptyElementosListUsuarios();
            }break;
            case 2:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                String idUsuario = lector.nextLine();
                lector.nextLine();
                isEmptyElementosListPrendas(idUsuario);
            }break;
            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseAnadir(){//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE2);
        int opcionCase2 = lector.nextInt();
        switch (opcionCase2){
            case 1:{
                insertarElementoUsuario(new Usuario());
            }break;
            case 2:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();
                System.out.println(Constantes.INSERTAR_NOMBRE_USUARIO);
                String nombre = lector.nextLine();
                insertarElementoUsuario(idUsuario,nombre,new Prendas());
            }break;
            case 3:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                insertarElementoPrenda(idUsuario,new Prenda());
            }break;
            case 4:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                System.out.println(Constantes.INSERTAR_PRENDA);
                String id = lector.nextLine();
                String prenda = lector.nextLine();
                String talla = lector.nextLine();
                double precio = lector.nextDouble();

                insertarElementoPrenda(idUsuario,id,prenda,talla,LocalDate.now(),precio);
            }break;
            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseListas(){//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE3);
        int opcionCase3 = lector.nextInt();
        switch (opcionCase3){
            case 1:{
                for (int i = 0; i < getElementosUsuarios().size(); i++) {
                    System.out.println(getElementosUsuarios().get(i).toStringUsuario());
                }
            }break;
            case 2:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                String idUsuario = lector.nextLine();
                for (int i = 0; i < getElementosPrendas(idUsuario).size(); i++) {
                    System.out.println(getElementosPrendas(idUsuario).get(i).toStringPrenda());
                }

            }break;
            case 3:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                String idUsuario = lector.nextLine();
                System.out.println(Constantes.INSERTAR_TALLA_PRENDA);
                String talla = lector.nextLine();
                for (int i = 0; i < getElementosPrendasTalla(idUsuario,talla).size(); i++) {
                    System.out.println(getElementosPrendasTalla(idUsuario,talla).get(i).toStringPrenda());
                }
            }break;
            case 4:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                String idUsuario = lector.nextLine();
                System.out.println(Constantes.ASCENDENTE);
                int aux = lector.nextInt();
                boolean asc;
                if (aux==1){
                    asc=true;
                }else {
                    asc=false;
                }
                for (int i = 0; i < listadoOrdenadoPrendas(idUsuario,asc).size(); i++) {
                    System.out.println(listadoOrdenadoPrendas(idUsuario,asc).get(i).toStringPrenda());
                }
            }break;
            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseFactura(){//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE4);
        int opcionCase = lector.nextInt();
        switch (opcionCase){
            case 1:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                String idUsuario = lector.nextLine();
                lector.nextLine();

                System.out.println(mostrarFactura(servicio.getElementosUsuarios().get(servicio.verificarIdUsuario(idUsuario))));
            }break;
            case 2:{
                System.out.println(rankingMejoresGastadores());
            }break;
            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseModificar() throws ExcepcionCaracterEspecial {//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE5);
        int opcionCase = lector.nextInt();
        switch (opcionCase){
            case 1:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                System.out.println(Constantes.INSERTAR_NOMBRE_USUARIO);
                String nombre = lector.nextLine();

                modificarUsuarioNombre(idUsuario,nombre);
            }break;
            case 2:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                System.out.println(Constantes.INSERTAR_ID_PRENDA);
                lector.nextLine();
                String idPrenda = lector.nextLine();

                System.out.println(Constantes.INSERTAR_TALLA_PRENDA);
                lector.nextLine();
                String talla = lector.nextLine();
                modificarPrendaTalla(idUsuario,idPrenda,talla);
            }break;
            case 3:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                System.out.println(Constantes.INSERTAR_ID_PRENDA);
                lector.nextLine();
                String idPrenda = lector.nextLine();

                System.out.println(Constantes.INSERTAR_NOMBRE_PRENDA);
                lector.nextLine();
                String nombre = lector.nextLine();
                modificarPrendaNombre(idUsuario,idPrenda,nombre);
            }
            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseEliminar() {//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE6);
        int opcionCase = lector.nextInt();
        switch (opcionCase){
            case 1:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                eliminarElementoUsuario(idUsuario);
            }break;
            case 2:{
                System.out.println(Constantes.INSERTAR_ID_USUARIO);
                lector.nextLine();
                String idUsuario = lector.nextLine();

                System.out.println(Constantes.INSERTAR_ID_PRENDA);
                lector.nextLine();
                String idPrenda = lector.nextLine();

                eliminarElementoPrenda(idUsuario,idPrenda);
            }break;

            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseFicheros() throws FileNotFoundException {//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE7);
        int opcionCase = lector.nextInt();
        switch (opcionCase){
            case 1:{
                escribirFichero();
            }break;
            case 2:{
                leerFichero();
            }break;

            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }
    public void opcionCaseJson() {//Finish
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.CASE8);
        int opcionCase = lector.nextInt();
        switch (opcionCase){
            case 1:{
                GestionJson.escribirUsuariosJson(Constantes.FICHERO_JSON,servicio.getElementosUsuarios());
            }break;
            case 2:{
                leerJson();
            }break;

            default:{
                System.out.println(Constantes.OPCION_DEFAULT);
            }
        }
    }

}
