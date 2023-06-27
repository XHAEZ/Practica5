/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cobos
 */
public class Pasajero {
    private String nombre;
    private String numeroAsiento;

    public Pasajero(String nombre, String numeroAsiento) {
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }
}
