/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import model.Bus;


/**
 *
 * @author cobos
 */
public class ModeloTablaBus extends AbstractTableModel{
    
    private ListaEnlazada<Bus> datos = new ListaEnlazada<>();

    public ListaEnlazada<Bus> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Bus> datos) {
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
       Bus b = null;
        try {
            b = datos.get(arg0);
        } catch (Exception e) {
        }
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return b.getNumeroBus();
            case 2: return b.getRuta();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Numero Bus";
            case 2: return "Ruta";
            default: return null;
        }
    }
    
    
}


