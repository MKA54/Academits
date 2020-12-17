package ru.academits.maksimenko.graph_main;

import ru.academits.maksimenko.graph.Graph;

public class Main {
    public static void main(String[] args) {
        int[][] array1 = {
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        Graph graph1 = new Graph(array1);

        System.out.println("Обход графа в ширину:");
        graph1.visitInWidth(System.out::println);
        System.out.println();

        System.out.println("Обход графа в глубину:");
        graph1.visitInDepth(System.out::println);
        System.out.println();

        System.out.println("Обход графа в глубину с рекурсией:");
        graph1.visitInDepthRecursion(System.out::println);
        System.out.println();

        int[][] array2 = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 1},
                {0, 1, 0, 1, 1, 0},
        };

        Graph graph2 = new Graph(array2);

        System.out.println("Обход графа в глубину с рекурсией:");
        graph2.visitInDepthRecursion(System.out::println);
        System.out.println();

        System.out.println("Обход графа в глубину:");
        graph2.visitInDepth(System.out::println);
    }
}