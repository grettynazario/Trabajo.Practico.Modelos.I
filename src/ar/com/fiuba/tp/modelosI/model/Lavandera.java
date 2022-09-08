/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IngrithNazario
 */
public class Lavandera {

    private Logger logger = Logger.getLogger("Lavanderia");
    private int _cantidadRopa;
    private HashMap<String, ArrayList<String>> _incompatibilidades = new HashMap<>();
    private HashMap<String, String> _tiemposLavados = new HashMap<>();
    private Prendas _prendas;

    public Lavandera(File datos) {
        _prendas = new Prendas();
        logger.info("|Lavandera| separando ropa...");
        try {
            Scanner reader = new Scanner(datos);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                guardarInformacion(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            logger.warning("Error leyendo el archivo." + datos.getName());
        }
    }

    public void lavar() {
        logger.info("|Lavandera| Lavando ropa...");
        
        for (int i = 0; i < _cantidadRopa; i++) {
            String prenda_i = String.valueOf(i + 1);
            ArrayList<String> prendasIncompatibles = _incompatibilidades.get(prenda_i);
            logger.info("|Lavandera| lavando prenda: " + prenda_i);
            for (int j = 0; j < _cantidadRopa; j++) {
                String prenda_j = String.valueOf(j + 1);
                if (i != j && !prendasIncompatibles.contains(prenda_j)) {
                    _prendas.addPrendaCompatible(prenda_i, prenda_j);
                }
            }

        }
        logger.info("|Lavandera| prendas lavadas.");
    }

    private void guardarInformacion(String data) {
        //Problema
        if (data.startsWith("p")) {
            _cantidadRopa = Integer.valueOf(data.split(" ")[2]);
            for (int i = 0; i < _cantidadRopa; i++) {
                String prenda_i = String.valueOf(i + 1);
                _incompatibilidades.put(prenda_i, new ArrayList<String>());
                _prendas.add(prenda_i, new Prenda(prenda_i));
            }
        }
        //Incompatibilidades
        if (data.startsWith("e")) {
            String[] e = data.split(" ");
            ArrayList<String> incompatibles = _incompatibilidades.get(e[1]);
            incompatibles.add(e[2]);
        }
        //Tiempos de lavado
        if (data.startsWith("n")) {
            String[] n = data.split(" ");
            _prendas.addTiempoLavado(n[1], Integer.valueOf(n[2]));
        }
    }

    public void prepararRopaLavada() {
        try {
            FileWriter writer = new FileWriter("resources/lavadoOutput.txt");
            for (Prenda prenda : _prendas.getPrendas()) {
                logger.info("|Lavandera| doblabando prenda: " + prenda.getNombre());
                writer.write(prenda.getNombre() + " " + prenda.getPrendaMasSucia() + "\n");
            }
            writer.close();
            logger.info("|Lavandera| prendas dobladas con éxito.");
            logger.info("|Lavanderia| Archivo Solución creado con éxito.");

        } catch (IOException e) {
            logger.warning("Error intentando escribir el archivo solucion." + e.getMessage());
        }
    }

}
