package ru.t1.education;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {

    // Итеративная реализация алгоритма быстрой сортировки
    public static void main(String[] args) {
        int[] baseArray = {10, 7, 3, 8, 9, 1, 5};

        System.out.println("Unsorted Array: " + Arrays.toString(baseArray));
        var quickSort = new QuickSort();
        quickSort.quickSort(baseArray, 0, baseArray.length - 1);
        System.out.println("Unsorted Array: " + Arrays.toString(baseArray));
    }

    // Метод быстрой сортировки
    public void quickSort(int[] arr, int l, int h) {
        if (arr == null || arr.length == 0)
            return;

        if (l >= h)
            return;

        Stack<Integer> stack = new Stack<>();
        stack.push(l);
        stack.push(h);

        while (!stack.isEmpty()) {
            h = stack.pop();
            l = stack.pop();

            int pivotIndex = partition(arr, l, h);

            /* Если левая часть подмассива полностью переставленна
            тогда формируем (помещаем в стек новую границу диапазона)
            */
            if (pivotIndex - 1 > l) {
                stack.push(l);
                stack.push(pivotIndex - 1);
            }
            /* Если индекс перестановки не достиг максимальной границы массива
            тогда формируем (помещаем в стек правую границу нового диапазона)
            */
            if (pivotIndex + 1 < h) {
                stack.push(pivotIndex + 1);
                stack.push(h);
            }
        }
    }

    // Переставление элементов меньше опорного в рамках каждой партиции (подмассива)
    public static int partition(int[] arr, int low, int high) {
        /* В качестве опорного элемента всегда выбирается последний элемент ->
        схема разделения Ломуто
        */
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }
}
