/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author IngrithNazario
 */
class Lavado {

    private final String _nombre;
    private List<Prenda> _prendas = new ArrayList<Prenda>();

    public Lavado(final String nombre) {
        _nombre = nombre;
    }

    Lavado(String nombre, Prenda prenda) {
        _nombre = nombre;
        _prendas.add(prenda);
    }

    public boolean add(Prenda prenda) {
        if (esCompatible(prenda)) {
            _prendas.add(prenda);
            return true;
        }
        return false;
    }

    public Collection<Prenda> getPrendas() {
        return _prendas;
    }

    public String getNombre() {
        return _nombre;
    }

    /*boolean sePuedelavar(Prenda prenda) {
        for(Prenda prendaComp : _prendas){
            if(!prenda.esCompatible(prendaComp)){
                return false;
            }
        }
        return true;
    }*/
    public void addPrendaMenor(Prenda prenda) {
        _prendas.add(prenda);
    }

    private boolean esCompatible(Prenda prenda) {
        for (Prenda prendaCompatible  : _prendas) {
            if(!prenda.esCompatible(prendaCompatible.getNombre()) || !prendaCompatible.esCompatible(prenda.getNombre())){
                return false;
            }
        }
        return true;
    }
}
