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
    private List<Prenda> _prendasSinLavado;

    public Lavados() {
        _lavados = new HashMap<>();
        _prendasSinLavado = new ArrayList<>();
    }

    public void add(final String key, final Prenda prenda) {
        if (_lavados.containsKey(key)) {
            Lavado lavado = _lavados.get(key);
            if (!lavado.add(prenda)) {
                _prendasSinLavado.add(prenda);
            }
        } else {
            Lavado lavado = new Lavado(key, prenda);
            _lavados.put(key, lavado);
        }
    }

    public Collection<Lavado> getLavados() {
        return _lavados.values();
    }

    public void asignarPrendasSinLavados(final int cantidad) {
        int maximoLavados = cantidad;
        //int lavadoNuevo = cantidad +1;
        boolean noAgregado = true;
        for (Prenda prenda : _prendasSinLavado) {
            noAgregado = true;
            //Me fijo en los otros lavados mayor a su lavado
            for (int i = Integer.valueOf(prenda.getTiempoLavado()) + 1; i <= maximoLavados; i++) {
                Lavado lavado = _lavados.get(String.valueOf(i));
                if (lavado != null && noAgregado) {
                    if (lavado.add(prenda)) {
                        noAgregado = false;
                        break;
                    }
                }
            }
            //Agrego en un nuevo lavado
            if (noAgregado) {
                maximoLavados ++;
                Lavado lavado = new Lavado(String.valueOf(maximoLavados ), prenda);
                _lavados.put(String.valueOf(maximoLavados), lavado);
                
            }
        }
    }

}
