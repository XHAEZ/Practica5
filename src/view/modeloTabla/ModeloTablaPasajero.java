/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import model.Bus;
import model.Pasajero;

/**
 *
 * @author cobos
 */
public class ModeloTablaPasajero extends AbstractTableModel{
    private ListaEnlazada<Pasajero> datos = new ListaEnlazada<>();

    public ListaEnlazada<Pasajero> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Pasajero> datos) {
        this.datos = datos;
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
      return 3;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
       Pasajero p = null;
        try {
            p = datos.get(arg0);
        } catch (Exception e) {
        }
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return p.getNombre();
            case 2: return p.getNumeroAsiento();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Nombre Pasajero";
            case 2: return "Numero Asiento";
            default: return null;
        }
    }
}
