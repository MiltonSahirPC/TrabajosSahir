package org.example.ui;

import org.example.common.Constantes;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.ExcepcionCaracterEspecial;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Iniciando Main");

        EntradaSalida ent = new EntradaSalida();

        try {
            ent.menuPrincipal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}