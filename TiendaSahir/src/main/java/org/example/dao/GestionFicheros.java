package org.example.dao;

import org.example.domain.Prenda;
import org.example.domain.Usuario;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionFicheros {

    public static void escribirFichero(String miFichero, List<Usuario> listaUsuarios) {
        try (PrintWriter pw = new PrintWriter(miFichero)) {
            for (Usuario u : listaUsuarios) {
                pw.println(u.toStringFicheroUsuario());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Usuario> leerFichero(String miFichero) throws FileNotFoundException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Scanner lector = new Scanner(new File(miFichero));
        while (lector.hasNextLine()) {
            String linea = lector.nextLine();
            listaUsuarios.add(new Usuario(linea));
        }
        return listaUsuarios;
    }

}
