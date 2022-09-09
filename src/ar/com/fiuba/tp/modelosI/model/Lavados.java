/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author IngrithNazario
 */
public class Lavados {

    public HashMap<String, Lavado> _lavados;
    private List<Prenda> _prendasIncompatibles;

    public Lavados() {
        _lavados = new HashMap<>();
        _prendasIncompatibles = new ArrayList<>();
    }

    public void add(final String key, final Prenda prenda) {
        if (_lavados.containsKey(key)) {
            Lavado lavado = _lavados.get(key);
            if (!lavado.add(prenda)) {
                _prendasIncompatibles.add(prenda);
            }
        } else {
            Lavado lavado = new Lavado(key);
            lavado.add(prenda);
            _lavados.put(key, lavado);
        }
    }

    public Collection<Lavado> getLavados() {
        return _lavados.values();
    }

    public void asignarPrendasSinLavados() {
        boolean noAgregado = true;
        for (Prenda prenda : _prendasIncompatibles) {
            noAgregado = true;
            //Me fijo en los otros lavados mayor a su lavado
            for (int i = Integer.valueOf(prenda.getTiempoLavado()) + 1; i < 11; i++) {
                Lavado lavado = _lavados.get(String.valueOf(i));
                if (lavado != null && noAgregado) {
                    if (lavado.sePuedelavar(prenda)) {
                        lavado.addPrendaMenor(prenda);
                        noAgregado = false;
                    }
                }
            }
        }
    }

}
