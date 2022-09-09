package ar.com.fiuba.tp.modelosI.model;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author IngrithNazario
 */
public class Prendas {

    private final HashMap<String,Prenda>_prendas;
    
    public Prendas() {
        _prendas = new HashMap<String,Prenda>();
    }

    public void add(final String prenda, final Prenda canasto) {
       _prendas.put(prenda,canasto);
    }

    public void addPrendaCompatible(final String prenda,final  String compatible) {
        Prenda padre = _prendas.get(prenda);
        Prenda hijo = _prendas.get(compatible);
        padre.add(hijo);
    }

    void addTiempoLavado(final String prenda,final String tiempo) {
        Prenda canasto = _prendas.get(prenda);
        canasto.addTiempoLavado(tiempo);
    }

    public Collection<Prenda> getPrendas() {
       return _prendas.values();
    }

    public Prenda getPrenda(String key) {
        return _prendas.get(key);
    }

}
