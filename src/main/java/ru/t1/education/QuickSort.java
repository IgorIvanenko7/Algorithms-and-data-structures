package ru.t1.education;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {

    public static void main(String[] args) {
        int[] baseArray = {10, 7, 3, 8, 9, 1, 5};

        System.out.println("Unsorted Array: " + Arrays.toString(baseArray));
        var quickSort = new QuickSort();
        quickSort.quickSort(baseArray, 0, baseArray.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(baseArray));
    }

    /**
     * Итеративная реализация алгоритма быстрой сортировки.
     * Использует стек для хранения диапазонов подмассивов
     * вместо рекурсивных вызовов.
     */
    public void quickSort(int[] arr, int l, int h) {
        if (arr == null || arr.length == 0)
            return;

        if (l >= h)
            return;

        // Стек хранит пары индексов (левая и правая границы подмассива)
        Stack<Integer> stack = new Stack<>();
        stack.push(l);
        stack.push(h);

        while (!stack.isEmpty()) {
            h = stack.pop();
            l = stack.pop();

            // Разбиение подмассива и получение индекса опорного элемента
            int pivotIndex = partition(arr, l, h);

            /*
             * Если слева от опорного элемента есть элементы,
             * добавляем левый подмассив в стек
             */
            if (pivotIndex - 1 > l) {
                stack.push(l);
                stack.push(pivotIndex - 1);
            }
            /*
             * Если справа от опорного элемента есть элементы,
             * добавляем правый подмассив в стек
             */
            if (pivotIndex + 1 < h) {
                stack.push(pivotIndex + 1);
                stack.push(h);
            }
        }
    }

    /**
     * Разбиение массива по схеме Ломуто.
     * В качестве опорного элемента выбирается последний элемент подмассива.
     *
     * Все элементы <= pivot перемещаются в левую часть,
     * элементы > pivot — в правую.
     *
     * return окончательный индекс опорного элемента
     */
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
        // Помещаем опорный элемент на его окончательную позицию
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }
}
