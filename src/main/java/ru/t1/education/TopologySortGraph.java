package ru.t1.education;

import java.util.Arrays;
import java.util.LinkedList;

public class TopologySortGraph {
    /**
     *  Матрица смежности  ориентированного (направленного) ациклического графа
     *  показывает связь вершин и направление связи ("1")
     */
    static String[] name = {"A", "B", "C", "D"};
    static int[][] matrixSourceGraph = {
            {0, 1, 1, 0}, // A -> B, A -> C
            {0, 0, 0, 1}, // B -> D
            {0, 0, 0, 1}, // C -> D
            {0, 0, 0, 0}, // D никуда не ведет
    };

    public static void main(String[] args) {
        // Стартовое состояние каждой вершины
        int n = matrixSourceGraph.length;
        statePoint[] stateP = new statePoint[n];
        Arrays.fill(stateP, statePoint.White);
        LinkedList<Integer> result = new LinkedList<>();

        //Цикл по вершинам графа
        for (int i = 0; i < n; i++) {

            if (stateP[i] == statePoint.White) {
                // Обработка "White" вершины
                if(! dfs(i, matrixSourceGraph, stateP, result)) {
                    throw new RuntimeException(" В графе обнаружен Цикл");
                }
            }
        }

        System.out.println("--- Результат топологической сортировки ---");
        result.forEach(i -> System.out.print(name[i] + " "));
    }

    static boolean dfs(int v, int[][] matrix, statePoint[] state, LinkedList<Integer> result) {

        // Помечаем тек. вершину как "в процессе" -> серая
        state[v] = statePoint.Gray;

        // Находим всех соседей тек. вешины
        for (int neighbor = 0; neighbor < matrix.length; neighbor ++) {
            // Если есть ребро (связь)
            if (matrix[v][neighbor] != 0) {
                // Если сосед серый — ЦИКЛ!, выходим с false
                if (state[neighbor] == statePoint.Gray) {
                    return false;
                }
                /* Если вершина еще не посещена (White),
                + рекурсивно исследуем все исходящие из неё связи (новую строку матрицы)
                */
                if (state[neighbor] == statePoint.White) {
                    if (!dfs(neighbor, matrix, state, result)) {
                        return false;
                    }
                }
            }
        }

        state[v] = statePoint.Black; // Помечаем как обработанную (черная)
        result.addFirst(v); // Добавляем в начало списка
        return true;
    }

}

enum statePoint {
    White,
    Gray,
    Black
}


