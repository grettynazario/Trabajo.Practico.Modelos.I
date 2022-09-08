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

    private Logger logger = Logger.getLogger("Lavandera");
    private int _cantidadRopa;
    private HashMap<String, ArrayList<String>> _incompatibilidades = new HashMap<>();
    private HashMap<String, String> _tiemposLavados = new HashMap<>();
    private Prendas _prendas;

    public Lavandera(File datos) {
        _prendas = new Prendas();
        try {
            Scanner reader = new Scanner(datos);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                guardarInformacion(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error leyendo el archivo." + datos.getName(), e);
        }
    }

    public void lavar() {
        for (int i = 0; i < _cantidadRopa; i++) {
            String prenda_i = String.valueOf(i + 1);
            ArrayList<String> prendasIncompatibles = _incompatibilidades.get(prenda_i);
            for (int j = 0; j < _cantidadRopa; j++) {
                String prenda_j = String.valueOf(j + 1);
                if (i != j && !prendasIncompatibles.contains(prenda_j)) {
                    _prendas.addPrendaCompatible(prenda_i, prenda_j);
                }
            }

        }
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


}