package ru.t1.education;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.t1.education.MatrixToListEdges.matrixSourceGraph;
import static ru.t1.education.MatrixToListEdges.matrixToEdgeList;

public class MatrixToListEdgesTest {

    /**
     *  Тестовый метод проверки конверта матрицы в список ребер для неориентированного графа
     *  Колличество !null элементов матрицы =  Колличество ребер в списке
     */
    @Test()
    public void countEdgesNotOrientTest() {
        var countEdges = Arrays.stream(matrixSourceGraph)
                .flatMapToInt(Arrays::stream)
                .filter(item -> item != 0)
                .count();
        var sizeOrient = matrixToEdgeList(matrixSourceGraph, true).size();
        assertEquals(countEdges, sizeOrient,
                "Колличество ребер для неориентированного графа, не верно");
    }

    /**
     *  Тестовый метод проверки конверта матрицы в список ребер для ориентированного графа
     *  (Колличество !null элементов матрицы)/2 =  Колличество ребер в списке
     */
    @Test()
    public void countEdgesOrientTest() {
        var countEdges = Arrays.stream(matrixSourceGraph)
                .flatMapToInt(Arrays::stream)
                .filter(item -> item != 0)
                .count();
        var sizeOrient = matrixToEdgeList(matrixSourceGraph, false).size();
        assertEquals(countEdges/2, sizeOrient,
                "Колличество ребер для ориентированного графа, не верно");
    }
}
