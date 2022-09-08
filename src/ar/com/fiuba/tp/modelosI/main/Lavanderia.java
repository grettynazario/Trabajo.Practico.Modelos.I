/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fiuba.tp.modelosI.main;

import ar.com.fiuba.tp.modelosI.model.Lavandera;
import java.io.File;
import java.util.logging.Logger;

/**
 *
 * @author IngrithNazario
 */
public class Lavanderia {

    
    private static File _input;
    private static Lavandera _lavandera;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         _input = new File("resources/input.txt");
        _lavandera = new Lavandera(_input);
        _lavandera.lavar();
        
    }


}
