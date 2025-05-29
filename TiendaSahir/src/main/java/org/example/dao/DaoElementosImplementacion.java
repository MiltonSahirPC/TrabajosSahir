package org.example.dao;
import org.example.common.Comprobaciones;
import org.example.common.Constantes;
import org.example.common.ExcepcionCaracterEspecial;
import org.example.domain.Admin;
import org.example.domain.Prenda;
import org.example.domain.Usuario;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ui.Main;

public class DaoElementosImplementacion implements DaoElementos {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private Admin miAdmin;

    public DaoElementosImplementacion(){
        this.miAdmin = new Admin();
    }

    public DaoElementosImplementacion(Admin admin){
        miAdmin = admin;
    }

    @Override
    //Verificar posicion - ID
    public int verificarIdUsuario(String idUsuario){
        logger.info("Usuario Verificado");
        boolean seguir=true;
        int idVerificado=0;

        for (int i = 0; i < miAdmin.getMiListaUsuarios().getMisUsuarios().size() && seguir; i++) {
            if (Objects.equals(miAdmin.getMiListaUsuarios().getMisUsuarios().get(i).getId(), idUsuario)){
                idVerificado=i;
                seguir=false;
            }
        }

        return idVerificado;
    }
    @Override
    //Verificar posicion - ID    int idUsuario lo da verificarIdUsuario()
    public int verificarIdPrenda(int idUsuario,String idPrenda){
        logger.info("Prenda Verificado");
        boolean seguir=true;
        int idVerificado=0;

        for (int i = 0; i < miAdmin.getMiListaUsuarios().getMisUsuarios().get(idUsuario).getMiLista().getListaElementos().size() && seguir; i++) {
            if (Objects.equals(miAdmin.getMiListaUsuarios().getMisUsuarios().get(idUsuario).getMiLista().getListaElementos().get(i).getId(), idPrenda)){
                idVerificado=i;
                seguir=false;
            }
        }

        return idVerificado;
    }

    //Comprobar Si la lista de Usuarios y Prendas está vacia
    @Override
    public boolean isEmptyElementosListUsuarios() {
        boolean vacio=false;
        if (miAdmin.getMiListaUsuarios().getMisUsuarios().isEmpty() || miAdmin.getMiListaUsuarios().getMisUsuarios() == null){
            vacio=true;
        }
        return vacio;
    }
    @Override
    public boolean isEmptyElementosListPrendas(String idUsuario) {
        boolean vacio=false;

        if (miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().isEmpty() || miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos() == null){
            vacio=true;
        }
        return vacio;
    }

    //Añadir Usuario o Prenda a la Lista
    @Override
    public boolean insertarElementoUsuario(Usuario usuario){
        return miAdmin.getMiListaUsuarios().getMisUsuarios().add(usuario);
    }
    @Override
    public boolean insertarElementoUsuario(String idUsuario, String nombre, Prendas miLista){
        return miAdmin.getMiListaUsuarios().getMisUsuarios().add(new Usuario(idUsuario,nombre, miLista));
    }
    @Override
    public boolean insertarElementoPrenda(String idUsuario, Prenda prenda) {

        return miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().add(prenda);
    }
    @Override
    public boolean insertarElementoPrenda(String idUsuario ,String id, String prenda, String talla, LocalDate fecha,double precio) {

        return miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().add(new Prenda( id,  prenda,  talla,  fecha,precio));
    }

    //Obtener la Lista de Usuarios o Prendas
    @Override
    public List<Usuario> getElementosUsuarios(){
        return miAdmin.getMiListaUsuarios().getMisUsuarios();
    }
    @Override
    public List<Prenda> getElementosPrendas(String idUsuario) {

        return miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos();
    }
    @Override
    public List<Prenda> getElementosPrendasTalla(String idUsuario ,String talla) {

        List<Prenda> listaOrdenadaTalla = miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos();

        listaOrdenadaTalla.sort(Comparator.comparing(Prenda::getTalla));
        return listaOrdenadaTalla;
    }
    @Override
    public List<Prenda> listadoOrdenadoPrendas(String idUsuario, boolean ascendente) {

        List<Prenda> listaOrdenadaPrenda = miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos();

        if (ascendente){
            listaOrdenadaPrenda.sort(Comparator.comparing(Prenda::getPrenda));
        }else {
            listaOrdenadaPrenda.sort(Comparator.comparing(Prenda::getPrenda).reversed());
        }

        return listaOrdenadaPrenda;
    }

    //Mostrar Factura del usuario
    @Override
    public double precioTotalFactura(Usuario usuario){
        double prefioTotal=0;

        for (Prenda prenda : usuario.getMiLista().getListaElementos()) {
            prefioTotal = prefioTotal + prenda.getPrecio();
        }

        return prefioTotal;
    }
    @Override
    public StringBuilder mostrarFactura(Usuario usuario){
        StringBuilder SB = new StringBuilder();

        usuario.getMiLista().getListaElementos().forEach(Prenda -> SB.append(Prenda.getPrenda()).append(": ").append(Prenda.getPrecio()).append("\n"));
        SB.append("Precio Total= ").append(precioTotalFactura(usuario));
        return SB;
    }
    @Override
    public StringBuilder rankingMejoresGastadores(){
        StringBuilder SB = new StringBuilder();
        List<Usuario> rankingUsuarios = miAdmin.getMiListaUsuarios().getMisUsuarios();

        rankingUsuarios.sort((u1, u2) -> Double.compare(
                precioTotalFactura(u2),
                precioTotalFactura(u1)
        ));

        for (Usuario usuario : rankingUsuarios){
            SB.append(usuario.getNombre()).append(": ").append(precioTotalFactura(usuario)).append("\n");
        }
        return SB;
    }

    //Modificar Usuario o Prenda
    @Override
    public boolean modificarUsuarioNombre(String idUsuario, String nombre) throws ExcepcionCaracterEspecial {
        boolean modificado=false;

        Comprobaciones.comprobarPalabra(nombre);
        try {
            miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).setNombre(nombre);
            if (Objects.equals(miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getNombre(), nombre)){
                modificado=true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar"+e.getMessage(),e);
        }

        return modificado;
    }
    @Override
    public boolean modificarPrendaTalla(String idUsuario,String idPrenda, String talla) {
        boolean modificado = false;

        miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().get(verificarIdPrenda(verificarIdUsuario(idUsuario),idPrenda)).setTalla(talla);
        if (Objects.equals(miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().get(verificarIdPrenda(verificarIdUsuario(idUsuario),idPrenda)).getTalla(), talla)){
            modificado=true;
        }

        return modificado;
    }
    @Override
    public boolean modificarPrendaNombre(String idUsuario,String idPrenda, String prenda) {
        boolean modificado=false;

        miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().get(verificarIdPrenda(verificarIdUsuario(idUsuario),idPrenda)).setPrenda(prenda);
        if (Objects.equals(miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().get(verificarIdPrenda(verificarIdUsuario(idUsuario),idPrenda)).getPrenda(), prenda)){
            modificado=true;
        }
        return modificado;
    }

    //Eliminar Usuario o Prenda
    @Override
    public void eliminarElementoUsuario(Usuario usuario){
        miAdmin.getMiListaUsuarios().getMisUsuarios().remove(usuario);
    }
    @Override
    public boolean eliminarElementoUsuario(String idUsuario){
        return miAdmin.getMiListaUsuarios().getMisUsuarios().remove(miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)));
    }
    @Override
    public void eliminarElementoPrenda(String idUsuario,Prenda prenda) {
        miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().remove(prenda);
    }
    @Override
    public boolean eliminarElementoPrenda(String idUsuario,String idPrenda) {
        return miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().remove(miAdmin.getMiListaUsuarios().getMisUsuarios().get(verificarIdUsuario(idUsuario)).getMiLista().getListaElementos().get(verificarIdPrenda(verificarIdUsuario(idUsuario),idPrenda)));
    }

    @Override
    public void escribirFichero(){
        GestionFicheros.escribirFichero(Constantes.FICHERO_ESCRIBIR,miAdmin.getMiListaUsuarios().getMisUsuarios());
    }

    @Override
    public void leerFichero() throws FileNotFoundException {
        miAdmin.getMiListaUsuarios().setMisUsuarios(GestionFicheros.leerFichero(Constantes.FICHERO_ESCRIBIR));
    }

    @Override
    public void leerJson(){
        miAdmin.getMiListaUsuarios().setMisUsuarios(GestionJson.leerUsuariosJson(Constantes.FICHERO_JSON));
    }
}
