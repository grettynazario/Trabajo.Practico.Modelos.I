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
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author IngrithNazario
 */
public class Lavandera {

    private Logger logger = Logger.getLogger("Lavanderia");
    private int _cantidadRopa;
    private HashMap<String, ArrayList<String>> _incompatibilidades = new HashMap<>();
    private Prendas _prendas;
    private Canastos _canastos;
    private Lavados _lavados;

    public Lavandera(File datos) {
        _prendas = new Prendas();
        _canastos = new Canastos();
        _lavados = new Lavados();
        logger.info("|Lavandera| recibiendo ropa...");
        guardarInformacionDePrendas(datos);
        asignarPrendasCompatibles();
        asignarLavados();
    }

    public void lavar() {
        separarPrendas();
        hacerLavados();
    }

    private void separarPrendas() {
        logger.info("|Lavandera| Separando prendas en sus correspondientes lavados...");
//        for (Prenda prenda : _prendas.getPrendas()) {
//            logger.info("|Lavandera| separando prenda: " + prenda.getNombre());
//            //_canastos.asignarCanasto(prenda);
//           
//        }
        _lavados.asignarPrendasSinLavados();
        logger.info("|Lavandera| prendas separadas.");
    }

    private void hacerLavados() {
        try {
            logger.info("|Lavandera| Lavando...");
            FileWriter writer = new FileWriter("resources/lavadoOutput.txt");
//            writer.write("c prendas sin lavado compatible\n");
//            for (Prenda prenda : _canastos.getPrendasSinCanasto()) {
//                writer.write("c " + prenda.getNombre() + "\n");
//            }
//            for (Canasto canasto : _canastos.getCanastos()) {
//                for (Prenda prenda : canasto.getPrendas()) {
//                    logger.info("|Lavandera| lavando prenda: " + prenda.getNombre());
//                    writer.write(prenda.getNombre() + " " + canasto.getNombreLavado() + "\n");
//                }
//            }
            for (Lavado lavado : _lavados.getLavados()) {
                for (Prenda prenda : lavado.getPrendas()) {
                    logger.info("|Lavandera| lavando prenda: " + prenda.getNombre());
                    writer.write(prenda.getNombre() + " " + lavado.getNombre() + "\n");
                }
            }
            writer.close();
            logger.info("|Lavandera| prendas lavadas con éxito.");
            logger.info("|Lavanderia| Archivo Solución creado con éxito.");

        } catch (IOException e) {
            logger.warning("Error intentando escribir el archivo solucion." + e.getMessage());
        }
    }

    private void asignarPrendasCompatibles() {
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

    private void guardarInformacionDePrendas(File datos) {
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

    private void guardarInformacion(String data) {
        //Problema
        if (data.startsWith("p")) {
            agregarMateriales(data);
        }
        //Incompatibilidades
        if (data.startsWith("e")) {
            asignarIncompatibilidad(data);
        }
        //Tiempos de lavado y Canastos por cada lavado
        if (data.startsWith("n")) {
            asignarTiempoLavado(data);
        }
    }

    private void asignarIncompatibilidad(String data) {
        String[] e = data.split(" ");
        ArrayList<String> incompatibles = _incompatibilidades.get(e[1]);
        incompatibles.add(e[2]);
    }

    private void asignarTiempoLavado(String data) {
        String[] n = data.split(" ");
        _prendas.addTiempoLavado(n[1], n[2]);
        _canastos.add(new Canasto(n[2]));        
    }

    private void agregarMateriales(String data) {
        _cantidadRopa = Integer.valueOf(data.split(" ")[2]);
        for (int i = 0; i < _cantidadRopa; i++) {
            String prenda_i = String.valueOf(i + 1);
            _incompatibilidades.put(prenda_i, new ArrayList<String>());
            _prendas.add(prenda_i, new Prenda(prenda_i));
        }
    }

    private void asignarLavados() {
        for(Prenda prenda : _prendas.getPrendas()){
            _lavados.add(prenda.getTiempoLavado(), prenda);
        }
    }
}
