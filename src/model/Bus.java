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
    private String ruta;
    private ListaEnlazada<Conductor> conductor = new ListaEnlazada<>();
    private ListaEnlazada<Pasajero> pasajero = new ListaEnlazada<>();

    public String getNumeroBus() {
        return numeroBus;
    }

    public void setNumeroBus(String numeroBus) {
        this.numeroBus = numeroBus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListaEnlazada<Conductor> getConductor() {
        return conductor;
    }

    public void setConductor(ListaEnlazada<Conductor> conductor) {
        this.conductor = conductor;
    }

    public ListaEnlazada<Pasajero> getPasajero() {
        return pasajero;
    }

    public void setPasajero(ListaEnlazada<Pasajero> pasajero) {
        this.pasajero = pasajero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
