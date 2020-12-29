package ru.academits.maksimenko.graph_main;

import ru.academits.maksimenko.graph.Graph;

public class Main {
    public static void main(String[] args) {
        int[][] array = {
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Graph graph = new Graph(array);

        System.out.println("Обход графа в ширину:");
        graph.visitInWidth(System.out::println);
        System.out.println();

        System.out.println("Обход графа в глубину:");
        graph.visitInDepth(System.out::println);
        System.out.println();

        System.out.println("Обход графа в глубину с рекурсией:");
        graph.visitInDepthRecursion(System.out::println);
    }
}