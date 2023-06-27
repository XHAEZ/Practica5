/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

/**
 *
 * @author cobos
 */
import java.util.LinkedList;

public class LinkedListBinarySearch {

    public static LinkedList<Integer> binarySearch(LinkedList<Integer> linkedList, int target) {
        LinkedList<Integer> result = new LinkedList<>();

        // Convertir la lista enlazada a un arreglo
        Integer[] array = linkedList.toArray(new Integer[linkedList.size()]);

        // Búsqueda binaria en el arreglo
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                result.add(target);
                return result;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result; // Retorna una lista enlazada vacía si no se encuentra el elemento
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(10);
        linkedList.add(8);
        linkedList.add(10);

        int target = 10;
        LinkedList<Integer> searchResult = binarySearch(linkedList, target);

        if (searchResult.isEmpty()) {
            System.out.println("Elemento no encontrado");
        } else {
            System.out.println("Elemento encontrado: " + searchResult.getFirst());
        }
    }
}
