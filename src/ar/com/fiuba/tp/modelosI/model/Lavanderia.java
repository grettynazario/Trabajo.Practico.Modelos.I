package ar.com.fiuba.tp.modelosI.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author IngrithNazario
 */
public class Lavanderia {

    private final File _input;
    private final Lavandera _lavandera;

    public Lavanderia() {
         _input = new File("resources/input.txt");
        _lavandera = new Lavandera(_input);
    }

    public void lavarRopa() {
        _lavandera.lavar();
    }

}
