/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ed.lista.ListaEnlazada;

/**
 *
 * @author cobos
 */
public class Bus {

    private Integer id;
    private String numeroBus;
    private Conductor conductor;
    private ListaEnlazada<Pasajero> pasajeros;

    public String getNumeroBus() {
        return numeroBus;
    }

    public void setNumeroBus(String numeroBus) {
        this.numeroBus = numeroBus;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public ListaEnlazada<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ListaEnlazada<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return numeroBus;
    }

}