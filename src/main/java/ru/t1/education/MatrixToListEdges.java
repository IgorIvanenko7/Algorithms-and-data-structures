package ru.t1.education;
import java.util.ArrayList;
import java.util.List;

public class MatrixToListEdges {

    /**
    *  Матрица смежности неориентированного графа приведенного в лекции
    *  каждый элемент указывает на наличие (и вес) ребра между вершинами "i" и "j".
    */
   static String[] name = {"A", "B", "C", "D", "E"};
   static int[][] matrixSourceGraph = {
            {0, 5, 0, 0, 0},
            {5, 0, 3, 7, 0},
            {0, 3, 0, 0, 2},
            {0, 7, 0, 0, 1},
            {0, 0, 2, 1, 0}
    };

    /**
     *  V1. Выводим список ребер для неориентированного графа
     *  V2. Выводим список ребер для ориентированного графа
     *     (ребро один раз -> «верхний треугольник матрицы»)
     */
    public static void main(String[] args) {

        // V1. неориентированный граф
        var listEdgesNotOrient = matrixToEdgeList(matrixSourceGraph, true);
        printEdgeList(listEdgesNotOrient);

        // V2. ориентированный граф (ребро один раз -> «верхний треугольник матрицы»)
        var listEdgesOrient = matrixToEdgeList(matrixSourceGraph, false);
        printEdgeList(listEdgesOrient);
    }

    /**
     *  Метод конверта матрицы в список для неориентированного/ориентированного графа
     */
    static List<Edge> matrixToEdgeList(int[][] matrix, boolean typeConvert) {
        List<Edge> edges = new ArrayList<>();
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            var startPos = typeConvert ? 0 : i;
            for (int j = startPos; j < n; j++) {
                // признак наличия ребра
                if (matrix[i][j] != 0) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        return edges;
    }

    static void printEdgeList(List<Edge> edgeList) {
        System.out.println("--- Список ребер ---");
        edgeList.forEach(edge ->
                System.out.println(edge.printEdge(name)));
    }

    /**
     *  Ребро графа - элемент списка
     *  source - исходящая вершина;
     *  destination - вершина назначения;
     *  weight - вес ребра между вершинами.
     */
      static class Edge {

            int source;
            int destination;
            int weight;

            public Edge(int source, int destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
            }

            public String printEdge(String[] arrName) {
                return String.format("Edge: %s --> %s == %d",
                        arrName[source], arrName[destination], weight);
            }
        }
}

