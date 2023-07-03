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

    public ListaEnlazada<Bus> buscarNumeroBus(String dato) {
        ListaEnlazada<Bus> lista = listar();
        mergeSort(lista, 0, 0);
        return busquedaBinariaNumeroBus(lista, dato);
    }
    
    public ListaEnlazada<Bus> buscarRuta(String dato) {
        ListaEnlazada<Bus> lista = listar();
        mergeSort(lista, 0, 1);
        return busquedaBinariaRuta(lista, dato);
    }
    
    public ListaEnlazada<Bus> buscarNumeroBusC(String dato) {
        ListaEnlazada<Bus> lista = listar();
        mergeSort(lista, 0, 0);
        return numeroBinarioSecuencial(lista, dato);
    }
    
     public ListaEnlazada<Bus> buscarRutaC(String dato) {
        ListaEnlazada<Bus> lista = listar();
        mergeSort(lista, 0, 1);
        return rutaBinarioSecuencial(lista, dato);
    }

    public ListaEnlazada<Bus> busquedaSecuencialNumero(ListaEnlazada<Bus> lista, String nombreBus) {
        Bus[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Bus

        ListaEnlazada<Bus> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Bus bus : arreglo) {
            String nombreBusAux = bus.getRuta(); // Obtiene el nombre de pasajero del objeto Bus

            if (nombreBusAux.equals(nombreBus)) {
                resultado.insertar(bus); // Agrega el objeto Bus encontrado a la lista
            }
        }

        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Bus encontrados
        } else {
            return null; // No se encontró ningún pasajero, se devuelve null
        }
    } 
    
    public ListaEnlazada<Bus> busquedaSecuencialRuta(ListaEnlazada<Bus> lista, String rutaBus) {
        Bus[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Bus

        ListaEnlazada<Bus> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Bus bus : arreglo) {
            String rutaBusAux = bus.getNumeroBus(); // Obtiene la ruta de pasajero del objeto Bus

            if (rutaBusAux.equals(rutaBus)) {
                resultado.insertar(bus); // Agrega el objeto Bus encontrado a la lista
            }
        }
        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Bus encontrados
        } else {
            return null; // No se encontró ningún pasajero, se devuelve null
        }
    } 
    
    public ListaEnlazada<Bus> numeroBinarioSecuencial(ListaEnlazada<Bus> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Bus> resultadoBinario = buscarNumeroBus(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialNumero(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
    
    public ListaEnlazada<Bus> rutaBinarioSecuencial(ListaEnlazada<Bus> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Bus> resultadoBinario = buscarRuta(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialRuta(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
  
    public ListaEnlazada<Bus> busquedaBinariaNumeroBus(ListaEnlazada<Bus> arreglo, String elemento) {
    Bus[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getNumeroBus().equals(elemento)) {
            ListaEnlazada<Bus> resultado = new ListaEnlazada<>();
            resultado.insertar(lista[medio]);
            return resultado;
        }

        // Si el elemento es mayor, se descarta la mitad izquierda del arreglo
        if (lista[medio].getNumeroBus().compareTo(elemento) < 0) {
            inicio = medio + 1;
        }
        // Si el elemento es menor, se descarta la mitad derecha del arreglo
        else {
            fin = medio - 1;
        }
    }

    // Si el elemento no se encuentra, se retorna una lista vacía
    return arreglo;
}
    
    public ListaEnlazada<Bus> busquedaBinariaRuta(ListaEnlazada<Bus> arreglo, String elemento) {
    Bus[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getRuta().equals(elemento)) {
            ListaEnlazada<Bus> resultado = new ListaEnlazada<>();
            resultado.insertar(lista[medio]);
            return resultado;
        }

        // Si el elemento es mayor, se descarta la mitad izquierda del arreglo
        if (lista[medio].getRuta().compareTo(elemento) < 0) {
            inicio = medio + 1;
        }
        // Si el elemento es menor, se descarta la mitad derecha del arreglo
        else {
            fin = medio - 1;
        }
    }

    // Si el elemento no se encuentra, se retorna una lista vacía
    return arreglo;
}
    
    public ListaEnlazada<Bus> mergeSort(ListaEnlazada<Bus> cuenta, Integer tipoOrden, Integer atributoOrden) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (cuenta == null || cuenta.size() <= 1) {
            return cuenta;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        Bus[] datos = cuenta.toArray();
        // Llama al método mergeSort para ordenar el arreglo
        mergeSort(datos, tipoOrden, atributoOrden);
        // Convierte el arreglo ordenado nuevamente en una lista enlazada
        return cuenta.toList(datos);
    }

    /**
     *
     * @param arreglo
     * @param tipoOrden
     */
    private static void mergeSort(Bus[] arreglo, int tipoOrden, Integer atributoOrden) {
        // Verifica si hay elementos para ordenar
        if (arreglo.length <= 1) {
            return;
        }
        // Divide el arreglo en dos mitades
        int mid = arreglo.length / 2;
        Bus[] arregloIzquierda = new Bus[mid];
        Bus[] arregloDerecha = new Bus[arreglo.length - mid];
        // Copia los elementos correspondientes a cada mitad en arreglos separados
        System.arraycopy(arreglo, 0, arregloIzquierda, 0, mid);
        System.arraycopy(arreglo, mid, arregloDerecha, 0, arreglo.length - mid);
        // Aplica mergeSort recursivamente a las dos mitades
        mergeSort(arregloIzquierda, tipoOrden, atributoOrden);
        mergeSort(arregloDerecha, tipoOrden, atributoOrden);
        // Combina las dos mitades ordenadas mediante el método merge
        merge(arreglo, arregloIzquierda, arregloDerecha, tipoOrden, atributoOrden);
    }

    /**
     *
     * @param arreglo
     * @param arregloIzquierda
     * @param arregloDerecha
     * @param tipoOrden
     */
    private static void merge(Bus[] arreglo, Bus[] arregloIzquierda, Bus[] arregloDerecha, int tipoOrden, Integer atributoOrden) {
        int leftSize = arregloIzquierda.length;
        int rightSize = arregloDerecha.length;
        int indiceIzquierda = 0;
        int indiceDerecha = 0;
        int indiceArreglo = 0;

        switch (atributoOrden) {
            case 0:
                // Combinación de las dos mitades en orden
                while (indiceIzquierda < leftSize && indiceDerecha < rightSize) {
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arregloIzquierda[indiceIzquierda].getNumeroBus().compareTo(arregloDerecha[indiceDerecha].getNumeroBus()) < 0) {
                            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                            indiceIzquierda++;
                        } else {
                            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                            indiceDerecha++;
                        }
                    }
                    indiceArreglo++;
                }
            case 1:    
             // Combinación de las dos mitades en orden
                while (indiceIzquierda < leftSize && indiceDerecha < rightSize) {
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arregloIzquierda[indiceIzquierda].getRuta().compareTo(arregloDerecha[indiceDerecha].getRuta()) < 0) {
                            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                            indiceIzquierda++;
                        } else {
                            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                            indiceDerecha++;
                        }
                    }
                    indiceArreglo++;
                }
           
            default:
        }

        // Copia los elementos restantes de la mitad izquierda, si los hay
        while (indiceIzquierda < leftSize) {
            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
            indiceIzquierda++;
            indiceArreglo++;
        }

        // Copia los elementos restantes de la mitad derecha, si los hay
        while (indiceDerecha < rightSize) {
            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
            indiceDerecha++;
            indiceArreglo++;
        }
    }

}
