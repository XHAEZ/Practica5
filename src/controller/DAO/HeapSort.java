/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

import controller.ed.lista.ListaEnlazada;

/**
 *
 * @author cobos
 */
public class HeapSort {
    public void sort(ListaEnlazada<Integer> list) {
        int n = list.size();
        Integer[] arr = list.toArray();

        // Construir el montículo (heap)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extraer elementos del montículo uno por uno
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Llamar al heapify en el subárbol reducido
            heapify(arr, i, 0);
        }

        // Actualizar la lista ordenada
        list.deleteAll();
        for (int i = 0; i < n; i++) {
            list.insertar(arr[i]);
        }
    }

    private static void heapify(Integer[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // Si el hijo izquierdo es más grande que la raíz
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Si el hijo derecho es más grande que la raíz
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Si el nodo más grande no es la raíz
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursivamente hacer el heapify en el subárbol afectado
            heapify(arr, n, largest);
        }
    }
}