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
import model.Conductor;

/**
 *
 * @author cobos
 */
public class ConductorDao extends AdaptadorDAO<Conductor> {

    private Conductor conductor;

    public ConductorDao() {
        super(Conductor.class);
    }

    public Conductor getConductor() {
        if (this.conductor == null) {
            this.conductor = new Conductor();
        }
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public void guardar() throws IOException {
        conductor.setId(generateID());
        this.guardar(conductor);
    }

    public void modificar(Integer pos) throws EmptyException, PositionException, IOException {
        this.modificar(conductor, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Conductor> buscarNombreConductor(String dato) {
        ListaEnlazada<Conductor> lista = listar();
        mergeSort(lista, 0, 0);
        return busquedaBinariaNombre(lista, dato);
    }
    
     public ListaEnlazada<Conductor> buscarCodigo(String dato) {
        ListaEnlazada<Conductor> lista = listar();
        mergeSort(lista, 0, 1);
        return busquedaBinariaCodigo(lista, dato);
    }
     
    public ListaEnlazada<Conductor> buscarNombreConductorC(String dato) {
        ListaEnlazada<Conductor> lista = listar();
        mergeSort(lista, 0, 0);
        return nombreBinarioSecuencial(lista, dato);
    }
    
     public ListaEnlazada<Conductor> buscarCodigoC(String dato) {
        ListaEnlazada<Conductor> lista = listar();
        mergeSort(lista, 0, 1);
        return codigoBinarioSecuencial(lista, dato);
    }

    public ListaEnlazada<Conductor> busquedaSecuencialNombre(ListaEnlazada<Conductor> lista, String nombreConductor) {
        Conductor[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Conductor

        ListaEnlazada<Conductor> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Conductor conductor : arreglo) {
            String nombreConductorAux = conductor.getNombre(); // Obtiene el nombre de conductor del objeto Conductor

            if (nombreConductorAux.equals(nombreConductor)) {
                resultado.insertar(conductor); // Agrega el objeto Conductor encontrado a la lista
            }
        }

        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Conductor encontrados
        } else {
            return null; // No se encontró ningún conductor, se devuelve null
        }
    } 
    
    public ListaEnlazada<Conductor> busquedaSecuencialCodigo(ListaEnlazada<Conductor> lista, String codigoConductor) {
        Conductor[] arreglo = lista.toArray(); // Convierte la lista enlazada a un arreglo de objetos Conductor

        ListaEnlazada<Conductor> resultado = new ListaEnlazada<>(); // Crea una nueva lista enlazada para almacenar los resultados

        for (Conductor conductor : arreglo) {
            String codigoConductorAux = conductor.getCodigoEmpleado(); // Obtiene el codigo de conductor del objeto Conductor

            if (codigoConductorAux.equals(codigoConductor)) {
                resultado.insertar(conductor); // Agrega el objeto Conductor encontrado a la lista
            }
        }
        if (!resultado.estaVacia()) {
            return resultado; // Devuelve la lista enlazada con los objetos Conductor encontrados
        } else {
            return null; // No se encontró ningún conductor, se devuelve null
        }
    } 
    
    public ListaEnlazada<Conductor> nombreBinarioSecuencial(ListaEnlazada<Conductor> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Conductor> resultadoBinario = buscarNombreConductor(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialNombre(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
    
    public ListaEnlazada<Conductor> codigoBinarioSecuencial(ListaEnlazada<Conductor> lista, String objetivo) {
        // Realizar la búsqueda binaria inicialmente
       ListaEnlazada<Conductor> resultadoBinario = buscarCodigo(objetivo);

        if (resultadoBinario.size() == -1) {
            // Si el elemento no se encuentra, realizar la búsqueda secuencial
            return busquedaSecuencialCodigo(lista, objetivo);
        } else {
            // Si se encuentra mediante búsqueda binaria, devolver el resultado
            return resultadoBinario;
        }
    }
    
    public ListaEnlazada<Conductor> busquedaBinariaNombre(ListaEnlazada<Conductor> arreglo, String elemento) {
    Conductor[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getNombre().equals(elemento)) {
            ListaEnlazada<Conductor> resultado = new ListaEnlazada<>();
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
    
    public ListaEnlazada<Conductor> busquedaBinariaCodigo(ListaEnlazada<Conductor> arreglo, String elemento) {
    Conductor[] lista = arreglo.toArray();
    int inicio = 0;
    int fin = lista.length - 1;

    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;

        // Si el elemento está en el medio, se encontró
        if (lista[medio].getCodigoEmpleado().equals(elemento)) {
            ListaEnlazada<Conductor> resultado = new ListaEnlazada<>();
            resultado.insertar(lista[medio]);
            return resultado;
        }

        // Si el elemento es mayor, se descarta la mitad izquierda del arreglo
        if (lista[medio].getCodigoEmpleado().compareTo(elemento) < 0) {
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
    
    public ListaEnlazada<Conductor> mergeSort(ListaEnlazada<Conductor> cuenta, Integer tipoOrden, Integer atributoOrden) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (cuenta == null || cuenta.size() <= 1) {
            return cuenta;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        Conductor[] datos = cuenta.toArray();
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
    private static void mergeSort(Conductor[] arreglo, int tipoOrden, Integer atributoOrden) {
        // Verifica si hay elementos para ordenar
        if (arreglo.length <= 1) {
            return;
        }
        // Divide el arreglo en dos mitades
        int mid = arreglo.length / 2;
        Conductor[] arregloIzquierda = new Conductor[mid];
        Conductor[] arregloDerecha = new Conductor[arreglo.length - mid];
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
    private static void merge(Conductor[] arreglo, Conductor[] arregloIzquierda, Conductor[] arregloDerecha, int tipoOrden, Integer atributoOrden) {
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
            case 1:
                // Combinación de las dos mitades en orden
                while (indiceIzquierda < leftSize && indiceDerecha < rightSize) {
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arregloIzquierda[indiceIzquierda].getCodigoEmpleado().compareTo(arregloDerecha[indiceDerecha].getCodigoEmpleado()) < 0) {
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
