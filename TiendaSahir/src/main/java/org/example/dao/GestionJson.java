package org.example.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GestionJson {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static void escribirUsuariosJson(String ruta, List<Usuario> listaUsuarios) {

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), listaUsuarios);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo JSON", e);
        }
    }

    public static List<Usuario> leerUsuariosJson(String json) {
        try {
            return mapper.readValue(new File(json), new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }
}