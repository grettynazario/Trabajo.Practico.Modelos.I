/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IngrithNazario
 */
public class Prenda {

    //private final ArrayList<Prenda> _prendasCompatibles;
    private final ArrayList<String> _prendasIncompatibles;
    private final String _nombre;
    private String _tiempoLavado;

    public Prenda(final String nombre) {
        //_prendasCompatibles = new ArrayList<Prenda>();
        _prendasIncompatibles = new ArrayList<String>();
        _nombre = nombre;
    }

    /*public void add(final Prenda prenda) {
        _prendasCompatibles.add(prenda);
    }*/

    public void addTiempoLavado(final String tiempo) {
        _tiempoLavado = tiempo;
    }

//    public String getPrendaMasSucia() {
//        int sucia = 0;
//        for (Prenda prenda : _prendasCompatibles) {
//            if (prenda.getTiempoLavado() > sucia) {
//                sucia = prenda.getTiempoLavado();
//            }
//        }
//        return String.valueOf(sucia);
//    }
    public String getTiempoLavado() {
        return _tiempoLavado;
    }

    public String getNombre() {
        return _nombre;
    }

   /* boolean esCompatible(Prenda aComparar) {
        for (Prenda prenda : _prendasCompatibles) {
            if (prenda.getNombre().equals(aComparar.getNombre())) {
                return true;
            }
        }
        return false;
    }*/

    /*public boolean esCompatible(List<Prenda> prendas) {
        for (Prenda prenda : prendas) {
            if (!this.esCompatible(prenda)) {
                return false;
            }
        }
        return true;
    }*/

    public void addPrendaIncompatible(String nombrePrenda) {
        _prendasIncompatibles.add(nombrePrenda);
    }

    public boolean esCompatible(String nombre) {
        return !_prendasIncompatibles.contains(nombre);
    }

}
