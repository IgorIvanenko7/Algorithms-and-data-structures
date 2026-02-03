package ru.t1.education;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.t1.education.Lcs.lcs;

public class LcsTest {

    /**
     *  Тестовый метод проверки алгоритма поиска наибольшей общей последовательности (строки)
     *  На вход подаются равные строки -> результирующая строка должна быть эквивалетной
     */
    @Test()
    public void detectEqualString() {
        String s1 = "ABDE";
        String s2 = "ABDE";
        assertEquals(s1, lcs(s1, s2),
                "Метод поиска наибольшей общей последовательности, реализован не верно");
    }

    /**
     *  Тестовый метод проверки алгоритма поиска наибольшей общей последовательности (строки)
     *  На вход подаются строки без совпадений -> результирующая строка должна быть пустой ""
     */
    @Test
    public void detectNotSubsequence() {
        String s1 = "ABC";
        String s2 = "XYZ";
        assertEquals("", lcs(s1, s2), "Должен возвращать пустую строку, если совпадений нет");
    }
}
