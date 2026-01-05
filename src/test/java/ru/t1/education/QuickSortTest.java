package ru.t1.education;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    static int[] BASE_ARRAY = {10, 7, 3, 8, 9, 1, 5};
    static int[] TRUE_SORT_ARRAY = {1, 3, 5, 7, 8, 9, 10};
    static int[] REVERSE_BASE_ARRAY = {10, 9, 8, 7, 5, 3, 1};

    // Тест: валидация сортировки - сравнение результата с native реализацией
    @Test()
    public void validateSortTest() {
        var copyBaseArr = Arrays.copyOf(BASE_ARRAY, BASE_ARRAY.length);
        var quickSort = new QuickSort();
        quickSort.quickSort(BASE_ARRAY, 0, BASE_ARRAY.length - 1);
        Arrays.sort(copyBaseArr);
        System.out.println(Arrays.toString(copyBaseArr));
        System.out.println(Arrays.toString(BASE_ARRAY));
        assertArrayEquals(copyBaseArr, BASE_ARRAY,
                "Реализация quickSort на несортированном массиве, невалидная");
    }

    // Тест: сортировка отсортированного массива
    @Test()
    public void validateTrueSortTest() {
        var copyBaseArr = Arrays.copyOf(TRUE_SORT_ARRAY, TRUE_SORT_ARRAY.length);
        var quickSort = new QuickSort();
        quickSort.quickSort(TRUE_SORT_ARRAY, 0, TRUE_SORT_ARRAY.length - 1);
        System.out.println(Arrays.toString(copyBaseArr));
        System.out.println(Arrays.toString(TRUE_SORT_ARRAY));
        assertArrayEquals(copyBaseArr, TRUE_SORT_ARRAY,
                "Реализация quickSort на сортированном массиве, невалидный");
    }

    // Тест: сортировка обратно отсортированного массива
    @Test()
    public void valideTest() {
        var copyBaseArr = Arrays.copyOf(REVERSE_BASE_ARRAY, REVERSE_BASE_ARRAY.length);
        var quickSort = new QuickSort();
        quickSort.quickSort(REVERSE_BASE_ARRAY, 0, REVERSE_BASE_ARRAY.length - 1);
        Arrays.sort(copyBaseArr);
        System.out.println(Arrays.toString(copyBaseArr));
        System.out.println(Arrays.toString(REVERSE_BASE_ARRAY));
        assertArrayEquals(copyBaseArr, REVERSE_BASE_ARRAY,
                "Реализация quickSort на реверсном массиве, невалидная");
    }
}
