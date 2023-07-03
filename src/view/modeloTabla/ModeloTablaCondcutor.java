/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import model.Bus;
import model.Conductor;

/**
 *
 * @author cobos
 */
public class ModeloTablaCondcutor extends AbstractTableModel {
    private ListaEnlazada<Conductor> datos = new ListaEnlazada<>();

    public ListaEnlazada<Conductor> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Conductor> datos) {
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
       Conductor c = null;
        try {
            c = datos.get(arg0);
        } catch (Exception e) {
        }
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return c.getNombre();
            case 2: return c.getCodigoEmpleado();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Nombre Conductor";
            case 2: return "Codigo Conductor";
            default: return null;
        }
    }
}
