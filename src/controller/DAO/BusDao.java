/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

import controller.ed.lista.ListaEnlazada;
import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import java.io.IOException;
import model.Bus;

/**
 *
 * @author cobos
 */
public class BusDao extends AdaptadorDAO<Bus> {

    private Bus bus;

    public BusDao() {
        super(Bus.class);
    }

    public Bus getBus() {
        if (this.bus == null) {
            this.bus = new Bus();
        }
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void guardar() throws IOException {
        bus.setId(generateID());
        this.guardar(bus);
    }

    public void modificar(Integer pos) throws EmptyException, PositionException, IOException {
        this.modificar(bus, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Bus> busquedaBinaria(ListaEnlazada<Bus> lista, String numeroBus) {
        Bus[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Bus
        quickSort(arreglo, 0, arreglo.length - 1); // Ordena el arreglo utilizando el algoritmo quickSort

        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2; // Calcula el índice medio del arreglo

            Bus aux = arreglo[medio]; // Obtiene el objeto Bus en el índice medio
            String numeroBusAux = aux.getNumeroBus(); // Obtiene el número de bus del objeto Bus

            if (numeroBusAux.toLowerCase().compareTo(numeroBus)>0) {
                ListaEnlazada<Bus> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados
                resultado.insertar(aux); // Agrega el objeto Bus encontrado a la lista
                return resultado; // Devuelve la lista enlazada con el objeto Bus encontrado
            } else if (numeroBusAux.compareTo(numeroBus) < 0) {
                inicio = medio + 1; // El número de bus buscado es mayor, se ajusta el inicio para buscar en la mitad derecha del arreglo
            } else {
                fin = medio - 1; // El número de bus buscado es menor, se ajusta el fin para buscar en la mitad izquierda del arreglo
            }
        }

        return null; // No se encontró el bus, se devuelve null
    }

    public ListaEnlazada<Bus> quickSort(ListaEnlazada<Bus> lista) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (lista == null || lista.size() <= 1) {
            return lista;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        Bus[] arreglo = lista.toArray();
        // Llama al método quickSort para ordenar el arreglo
        quickSort(arreglo, 0, arreglo.length - 1);
        // Convierte el arreglo ordenado nuevamente en una lista enlazada
        return lista.toList(arreglo);
    }

    /**
     *
     * @param arreglo
     * @param bajo
     * @param alto
     * @param tipoOrden
     */
    private static void quickSort(Bus[] arreglo, int bajo, int alto) {
        // Verifica si hay elementos para ordenar
        if (bajo < alto) {
            // Divide el arreglo y obtiene el índice de la división
            int indiceDivision = division(arreglo, bajo, alto);
            // Ordena recursivamente la mitad inferior antes de la división
            quickSort(arreglo, bajo, indiceDivision - 1);
            // Ordena recursivamente la mitad superior después de la división
            quickSort(arreglo, indiceDivision + 1, alto);
        }
    }

    /**
     *
     * @param arreglo
     * @param bajo
     * @param alto
     * @param tipoOrden
     * @return
     */
    private static int division(Bus[] arreglo, int bajo, int alto) {
        // Selecciona el pivote como el último elemento del arreglo
        Bus pivote = arreglo[alto];
        int i = bajo - 1;
        // Itera sobre el arreglo desde el índice bajo hasta el índice alto - 1
        for (int j = bajo; j < alto; j++) {
            if (arreglo[j].getNumeroBus().compareTo(pivote.getNumeroBus()) < 0) {
                i++;
                intercambio(arreglo, i, j);
            }
        }
        // Intercambia el pivote con el elemento en la posición i + 1

        intercambio(arreglo, i + 1, alto);
        // Devuelve la posición del pivote después de la división
        return i + 1;
    }

    /**
     *
     * @param arreglo
     * @param i
     * @param j
     */
    private static void intercambio(Bus[] arreglo, int i, int j) {
        // Intercambia los elementos en las posiciones i y j del arreglo
        Bus temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static void main(String[] args) {
        BusDao bus = new BusDao();
        try {
            bus.getBus().setNumeroBus("LA1043");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LA103");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LA1043");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LQ10439");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LI1044");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LU1041");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LO1047");
            bus.guardar();
            bus.setBus(null);
            bus.getBus().setNumeroBus("LU1042");
            bus.guardar();
            bus.setBus(null);
            bus.listar().imprimir();
            System.out.println(bus.busquedaBinaria(bus.listar(), "LA1043"));
        } catch (Exception e) {
        }

    }
}
