package org.example.dao;

import org.example.common.ExcepcionCaracterEspecial;
import org.example.dao.DaoElementos;
import org.example.dao.Prendas;
import org.example.dao.Usuarios;
import org.example.domain.Admin;
import org.example.domain.Prenda;
import org.example.domain.Usuario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class DaoElementosImplementacionTest {

    private final Admin admin = new Admin();
    private final DaoElementosImplementacion daoImp = new DaoElementosImplementacion(admin);

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
        Usuario usuario = new Usuario("1",nombre, new Prendas());

        boolean respuesta = daoImp.insertarElementoUsuario(usuario);

        assertThat(respuesta).isTrue();

    }

    @Test
    @Order(2)
    void getElementosPrendas(){
        Prenda prenda1 = new Prenda("1","CPolo","M",LocalDate.now(),20);
        Prenda prenda2 = new Prenda("1","BPolo","M",LocalDate.now(),20);
        Prenda prenda3 = new Prenda("1","APolo","M",LocalDate.now(),20);
        Prendas misPrendas = new Prendas();
        misPrendas.getListaElementos().add(prenda1);
        misPrendas.getListaElementos().add(prenda2);
        misPrendas.getListaElementos().add(prenda3);

        Usuario usuario = new Usuario("1","Sahir",misPrendas);

        assertThat(usuario.getMiLista().getListaElementos()).isEqualTo(misPrendas.getListaElementos());
    }

    @Test
    @Order(3)
    void listadoOrdenadoPrendas(){
        Prenda prenda1 = new Prenda("1","CPolo","M",LocalDate.now(),20);
        Prenda prenda2 = new Prenda("1","BPolo","M",LocalDate.now(),20);
        Prenda prenda3 = new Prenda("1","APolo","M",LocalDate.now(),20);
        Prendas misPrendas = new Prendas();
        misPrendas.getListaElementos().add(prenda1);
        misPrendas.getListaElementos().add(prenda2);
        misPrendas.getListaElementos().add(prenda3);

        Usuario usuario = new Usuario("1","Sahir",misPrendas);
        daoImp.insertarElementoUsuario(usuario);
        daoImp.listadoOrdenadoPrendas("1",true);

        assertThat(usuario.getMiLista().getListaElementos()).containsExactly(prenda3,prenda2,prenda1);

    }

    @Test
    @Order(4)
    void rankingMejoresGastadores(){
        Prenda prenda1 = new Prenda("1","CPolo","M",LocalDate.now(),10);
        Prenda prenda2 = new Prenda("2","BPolo","M",LocalDate.now(),20);
        Prenda prenda3 = new Prenda("3","APolo","M",LocalDate.now(),30);
        Prendas misPrendas1 = new Prendas();
        Prendas misPrendas2 = new Prendas();
        Prendas misPrendas3 = new Prendas();
        misPrendas1.getListaElementos().add(prenda1);
        misPrendas2.getListaElementos().add(prenda2);
        misPrendas3.getListaElementos().add(prenda3);

        Usuario usuario1 = new Usuario("1","Sahir1",misPrendas1);
        Usuario usuario2 = new Usuario("2","Sahir2",misPrendas2);
        Usuario usuario3 = new Usuario("3","Sahir3",misPrendas3);

        daoImp.insertarElementoUsuario(usuario1);
        daoImp.insertarElementoUsuario(usuario2);
        daoImp.insertarElementoUsuario(usuario3);

        StringBuilder lista = daoImp.rankingMejoresGastadores();
        String ranking = lista.toString();
        String esperado = "Sahir3: 30.0\nSahir2: 20.0\nSahir1: 10.0\n";
        assertThat(ranking).isEqualTo(esperado);
    }

    @Test
    @Order(5)
    void modificarPrendaNombre(){
        Prenda prenda = new Prenda("1", "Camiseta", "M", LocalDate.now(), 10);
        Prendas prendas = new Prendas();
        prendas.getListaElementos().add(prenda);
        Usuario usuario = new Usuario("1", "Sahir", prendas);

        daoImp.insertarElementoUsuario(usuario);

        boolean modificado = daoImp.modificarPrendaNombre("1", "1", "Sudadera");

        assertThat(modificado).isTrue();
    }

}