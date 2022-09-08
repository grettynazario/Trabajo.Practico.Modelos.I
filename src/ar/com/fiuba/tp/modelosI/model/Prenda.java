/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.model;

import java.util.ArrayList;

/**
 *
 * @author IngrithNazario
 */
public class Prenda {

    private final ArrayList<Prenda> _prendasCompatibles;
    private final String _nombre;
    private int _tiempoLavado;

    public Prenda(final String nombre) {
        _prendasCompatibles = new ArrayList<Prenda>();
        _nombre = nombre;
    }

    public void add(final Prenda prenda) {
        _prendasCompatibles.add(prenda);
    }

    public void addTiempoLavado(final int tiempo) {
        _tiempoLavado = tiempo;
    }
    
    public String getPrendaMasSucia() {
        int sucia = 0;
        for (Prenda prenda : _prendasCompatibles) {
            if (prenda.getTiempoLavado() > sucia) {
                sucia = prenda.getTiempoLavado();
            }
        }
        return String.valueOf(sucia);
    }

    public int getTiempoLavado() {
        return _tiempoLavado;
    }

    public String getNombre() {
        return _nombre;
    }
}
