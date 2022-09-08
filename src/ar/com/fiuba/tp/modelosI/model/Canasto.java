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
class Canasto {

    public String _nombre;
    public List<Prenda> _prendas = new ArrayList<Prenda>();

    public Canasto(final String nombre) {
        _nombre = nombre;
    }

    public void addPrenda(Prenda prenda) {
        _prendas.add(prenda);
    }

    boolean esCompatible(Prenda aComparar) {
        if (esDeMayorIgualLavado(aComparar)) {
            if (_prendas.size() == 0) {
                return true;
            }
            for (Prenda prenda : _prendas) {
                if (!prenda.esCompatible(aComparar)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getNombreLavado() {
        return _nombre;
    }

    public List<Prenda> getPrendas() {
        return _prendas;
    }

    private boolean esDeMayorIgualLavado(Prenda aComparar) {
        return Integer.valueOf(_nombre) >= Integer.valueOf(aComparar.getTiempoLavado());
    }

}
