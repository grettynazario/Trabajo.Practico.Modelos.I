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
import java.util.logging.Logger;

/**
 *
 * @author IngrithNazario
 */
public class Canastos {

    private final Logger logger = Logger.getLogger("Lavanderia");
    public HashMap<String, Canasto> _canastos;
    public List<Prenda> _prendasSinCanasto = new ArrayList<>();

    public Canastos() {
        _canastos = new HashMap<>();
    }

    public void asignarCanasto(Prenda prenda) {
        //me fijo en su propia canasta (lavado)
        Canasto canasto = _canastos.get(prenda.getTiempoLavado());
        if (canasto.getPrendas().isEmpty() || canasto.esCompatible(prenda)) {
            logger.info("|Lavandera| prenda: " + prenda.getNombre() + " asignado a lavado " + canasto.getNombreLavado());
            canasto.addPrenda(prenda);
            return;
        }
        //Me fijo en las otras otras
        for (int i = Integer.valueOf(prenda.getTiempoLavado()) + 1; i<10; i++) {
            Canasto siguienteCanasta = _canastos.get(String.valueOf(i));
            if(_canastos.get(String.valueOf(i)) != null){
                if (siguienteCanasta.esCompatible(prenda)) {
                    logger.info("|Lavandera| prenda: " + prenda.getNombre() + " asignado a lavado " + siguienteCanasta.getNombreLavado());
                    siguienteCanasta.addPrenda(prenda);
                    return;
                }
                
            }
        }
       //No hay canasto compatible
       logger.info("|Lavandera| prenda: " + prenda.getNombre() + " no asignado a un lavado compatible. ");
       _prendasSinCanasto.add(prenda);

    }

    public Collection<Canasto> getCanastos() {
        return _canastos.values();
    }
    
     public List<Prenda> getPrendasSinCanasto() {
        return _prendasSinCanasto;
    }

    public void add(final Canasto canasto) {
        _canastos.put(canasto.getNombreLavado(), canasto);
    }

}
