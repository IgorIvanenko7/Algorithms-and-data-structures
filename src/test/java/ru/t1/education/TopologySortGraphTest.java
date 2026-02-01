package ru.t1.education;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.t1.education.TopologySortGraph.dfs;

public class TopologySortGraphTest {

    /**
     *  Матрица смежности ориентированного (направленного) графа
     *  с ЦИКЛИЧЕСКОЙ ("D -> A") связью для Unit теста
     */
    static int[][] matrixSourceTopologyCycleGraph = {
            {0, 1, 1, 0}, // A -> B, A -> C
            {0, 0, 0, 1}, // B -> D
            {0, 0, 0, 1}, // C -> D
            {3, 0, 0, 0}, // D -> A
    };

    /**
     *  Тестовый метод проверки алгоритма топологической сортировки графа
     *  на выявление цикла
     */
    @Test()
    public void detectCycleInGraphTest() {

        int n = matrixSourceTopologyCycleGraph.length;
        statePoint[] stateP = new statePoint[n];
        Arrays.fill(stateP, statePoint.White);
        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (stateP[i] == statePoint.White) {
                assertFalse(dfs(i, matrixSourceTopologyCycleGraph, stateP, result),
                        "Цикл в графе не выявлен");
            }
        }
    }
}
