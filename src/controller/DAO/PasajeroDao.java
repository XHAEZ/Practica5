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
import model.Pasajero;

/**
 *
 * @author cobos
 */
public class PasajeroDao extends AdaptadorDAO<Pasajero> {

    private Pasajero pasajero;

    public PasajeroDao() {
        super(Pasajero.class);
    }

    public Pasajero getPasajero() {
        if (this.pasajero == null) {
            this.pasajero = new Pasajero();
        }
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void guardar() throws IOException {
        pasajero.setId(generateID());
        this.guardar(pasajero);
    }

    public void modificar(Integer pos) throws EmptyException, PositionException, IOException {
        this.modificar(pasajero, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Pasajero> buscarNumeroAsiento(String dato) {
        ListaEnlazada<Pasajero> lista = listar();
        mergeSort(lista, 0, 0);
        return busquedaBinariaNumeroAsiento(lista, dato);
    }
    

    public ListaEnlazada<Pasajero> buscarNombrePasajero(String dato) {
        ListaEnlazada<Pasajero> lista = listar();
        mergeSort(lista, 0, 1);
        return busquedaBinariaNombre(lista, dato);
    }

    public ListaEnlazada<Pasajero> buscarNumeroAsientoC(String dato) {
        ListaEnlazada<Pasajero> lista = listar();
        mergeSort(lista, 0, 0);
        return asientoBinarioSecuencial(lista, dato);
    }
    
     public ListaEnlazada<Pasajero> buscarNombreC(String dato) {
        ListaEnlazada<Pasajero> lista = listar();
        mergeSort(lista, 0, 1);
        return nombreBinarioSecuencial(lista, dato);
    }

    public ListaEnlazada<Pasajero> busquedaSecuencialNombre(ListaEnlazada<Pasajero> lista, String nombrePasajero) {
        Pasajero[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Pasajero

        ListaEnlazada<Pasajero> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Pasajero pasajero : arreglo) {
            String nombrePasajeroAux = pasajero.getNombre(); // Obtiene el nombre de pasajero del objeto Pasajero

            if (nombrePasajeroAux.equals(nombrePasajero)) {
                resultado.insertar(pasajero); // Agrega el objeto Pasajero encontrado a la lista
            }
        }

        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Pasajero encontrados
        } else {
            return null; // No se encontró ningún pasajero, se devuelve null
        }
    } 
    
    public ListaEnlazada<Pasajero> busquedaSecuencialAsiento(ListaEnlazada<Pasajero> lista, String asientoPasajero) {
        Pasajero[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Pasajero

        ListaEnlazada<Pasajero> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Pasajero pasajero : arreglo) {
            String asientoPasajeroAux = pasajero.getNumeroAsiento(); // Obtiene el asiento de pasajero del objeto Pasajero

            if (asientoPasajeroAux.equals(asientoPasajero)) {
                resultado.insertar(pasajero); // Agrega el objeto Pasajero encontrado a la lista
            }
        }
        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Pasajero encontrados
        } else {
            return null; // No se encontró ningún pasajero, se devuelve null
        }
    } 
    
    public ListaEnlazada<Pasajero> nombreBinarioSecuencial(ListaEnlazada<Pasajero> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Pasajero> resultadoBinario = buscarNombrePasajero(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialNombre(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
    
    public ListaEnlazada<Pasajero> asientoBinarioSecuencial(ListaEnlazada<Pasajero> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Pasajero> resultadoBinario = buscarNumeroAsiento(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialAsiento(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
    
    public ListaEnlazada<Pasajero> busquedaBinariaNumeroAsiento(ListaEnlazada<Pasajero> arreglo, String elemento) {
    Pasajero[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getNumeroAsiento().equals(elemento)) {
            ListaEnlazada<Pasajero> resultado = new ListaEnlazada<>();
            resultado.insertar(lista[medio]);
            return resultado;
        }

        // Si el elemento es mayor, se descarta la mitad izquierda del arreglo
        if (lista[medio].getNumeroAsiento().compareTo(elemento) < 0) {
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
    
    public ListaEnlazada<Pasajero> busquedaBinariaNombre(ListaEnlazada<Pasajero> arreglo, String elemento) {
    Pasajero[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getNombre().equals(elemento)) {
            ListaEnlazada<Pasajero> resultado = new ListaEnlazada<>();
            resultado.insertar(lista[medio]);
            return resultado;
        }

        // Si el elemento es mayor, se descarta la mitad izquierda del arreglo
        if (lista[medio].getNombre().compareTo(elemento) < 0) {
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
    
    public ListaEnlazada<Pasajero> mergeSort(ListaEnlazada<Pasajero> cuenta, Integer tipoOrden, Integer atributoOrden) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (cuenta == null || cuenta.size() <= 1) {
            return cuenta;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        Pasajero[] datos = cuenta.toArray();
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
    private static void mergeSort(Pasajero[] arreglo, int tipoOrden, Integer atributoOrden) {
        // Verifica si hay elementos para ordenar
        if (arreglo.length <= 1) {
            return;
        }
        // Divide el arreglo en dos mitades
        int mid = arreglo.length / 2;
        Pasajero[] arregloIzquierda = new Pasajero[mid];
        Pasajero[] arregloDerecha = new Pasajero[arreglo.length - mid];
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
    private static void merge(Pasajero[] arreglo, Pasajero[] arregloIzquierda, Pasajero[] arregloDerecha, int tipoOrden, Integer atributoOrden) {
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
                        if (arregloIzquierda[indiceIzquierda].getNumeroAsiento().compareTo(arregloDerecha[indiceDerecha].getNumeroAsiento()) < 0) {
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
                        if (arregloIzquierda[indiceIzquierda].getNombre().compareTo(arregloDerecha[indiceDerecha].getNombre()) < 0) {
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
