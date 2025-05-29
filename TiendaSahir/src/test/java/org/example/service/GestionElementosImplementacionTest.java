package org.example.service;

import org.example.common.ExcepcionCaracterEspecial;
import org.example.dao.DaoElementos;
import org.example.dao.Prendas;
import org.example.dao.Usuarios;
import org.example.domain.Prenda;
import org.example.domain.Usuario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class GestionElementosImplementacionTest {

    @Mock
    DaoElementos daoElementos;
    @InjectMocks
    GestionElementosImplementacion gestion;

    @BeforeEach
    void inicio(){
        System.out.println("Empezando prueba");
    }
    @AfterEach
    void terminar(){
        System.out.println("Prueba Terminada");
    }

    @ParameterizedTest
    @DisplayName("Insertar Usuario")
    @Order(1)
    @ValueSource(strings = {"Sahir","Juan","Juan2"})
    void insertarElementoUsuario(String nombre) {

        Prendas miLista = new Prendas("1","Polo","M", LocalDate.of(2025,5,20) ,20);
        Usuario usuario = new Usuario("1",nombre,miLista);

        //when
        when(daoElementos.insertarElementoUsuario(usuario)).thenReturn(true);
        boolean respuesta = gestion.insertarElementoUsuario(usuario);
        Assertions.assertTrue(respuesta);

    }

    @Nested
    @Order(4)
    public class listasElementos{

        @Test
        @Order(6)
        public void getElementosUsuarios() {
            Usuarios listaUsuarios = new Usuarios();

            Prendas miLista = new Prendas("1","Polo","M", LocalDate.of(2025,5,20) ,20);
            Usuario usuario1 = new Usuario("1","Sahir1",miLista);
            Usuario usuario2 = new Usuario("2","Sahir2",miLista);
            Usuario usuario3 = new Usuario("3","Sahir3",miLista);

            listaUsuarios.getMisUsuarios().add(usuario1);
            listaUsuarios.getMisUsuarios().add(usuario2);
            listaUsuarios.getMisUsuarios().add(usuario3);

            when(daoElementos.getElementosUsuarios()).thenReturn(listaUsuarios.getMisUsuarios());
            Assertions.assertEquals(listaUsuarios.getMisUsuarios(),gestion.getElementosUsuarios());
        }

        @Test
        @Order(5)
        public void getElementosPrendas() {
            Prendas listaPrendas = new Prendas();
            Prenda prenda1 = new Prenda();
            Prenda prenda2 = new Prenda();
            Prenda prenda3 = new Prenda();
            listaPrendas.getListaElementos().add(prenda1);
            listaPrendas.getListaElementos().add(prenda2);
            listaPrendas.getListaElementos().add(prenda3);

            Usuario usuario1 = new Usuario("1","Sahir1",listaPrendas);

            when(daoElementos.getElementosPrendas(usuario1.getId())).thenReturn(usuario1.getMiLista().getListaElementos());
            Assertions.assertEquals(listaPrendas.getListaElementos(),gestion.getElementosPrendas(usuario1.getId()));
        }
    }

    @Test
    @Order(7)
    @DisplayName("Precio Factura")
    public void precioTotalFactura() {

        Prendas misPrendas = new Prendas("1","Polo","M", LocalDate.of(2025,5,20) ,60);
        Usuario miUsuario = new Usuario("1","Sahir",misPrendas);

        when(daoElementos.precioTotalFactura(miUsuario)).thenReturn(60.0);

        double total = gestion.precioTotalFactura(miUsuario);
        Assertions.assertEquals(60,total);
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"Sahir","Juan","Juan2"})
    public void modificarUsuarioNombre(String nombre) throws ExcepcionCaracterEspecial {

        //when
        when(daoElementos.modificarUsuarioNombre("1",nombre)).thenReturn(true);
        boolean respuesta =gestion.modificarUsuarioNombre("1",nombre);

        Assertions.assertTrue(respuesta);
    }

    @Test
    @Order(8)
    public void modificarUsuarioNombreExcepcion() throws ExcepcionCaracterEspecial {

        String nombre="1";

        when(daoElementos.modificarUsuarioNombre("1", nombre))
                .thenThrow(new ExcepcionCaracterEspecial("La palabra contiene caracteres no válidos"));

        Assertions.assertThrows(ExcepcionCaracterEspecial.class, () -> {
            gestion.modificarUsuarioNombre("1", nombre);
        });
    }

    @Test
    @DisplayName("Eliminar Usuario")
    @Order(3)
    public void eliminarElementoUsuario() {

        when(daoElementos.eliminarElementoUsuario("2")).thenReturn(true);
        boolean respuesta = gestion.eliminarElementoUsuario("2");

        Assertions.assertTrue(respuesta);
    }
}