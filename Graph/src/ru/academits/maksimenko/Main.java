package ru.academits.maksimenko;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] graph = new int[10][10];

        graph[0][1] = 1;

        graph[1][0] = 1;
        graph[1][2] = 1;
        graph[1][3] = 1;

        graph[2][1] = 1;
        graph[2][3] = 1;

        graph[3][1] = 1;
        graph[3][2] = 1;

        graph[4][5] = 1;
        graph[5][4] = 1;

        graph[7][8] = 1;

        graph[8][7] = 1;
        graph[8][9] = 1;

        graph[9][8] = 1;

        System.out.println("Обход графа в ширину:");
        widthInVisit(graph);
        System.out.println();

        System.out.println("Обход графа в глубину:");
        depthInVisit(graph);
    }

    public static void widthInVisit(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        visited[0] = true;

        while (!queue.isEmpty()) {
            int top = queue.remove();

            System.out.println(top);

            for (int i = 1; i < graph[top].length; i++) {
                if (graph[top][i] == 1 && !visited[i]) {
                    queue.add(i);

                    visited[i] = true;
                }
            }

            for (int i = 1; i < visited.length; i++) {
                if (!visited[i]) {
                    queue.add(i);

                    visited[i] = true;
                }
            }
        }
    }

    public static void depthInVisit(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        visited[0] = true;

        while (!stack.isEmpty()) {
            int top = stack.removeLast();

            System.out.println(top);

            for (int i = 1; i < graph[top].length; i++) {
                if (graph[top][i] == 1 && !visited[i]) {
                    stack.addLast(i);

                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = visited.length - 1; i > 0; i--) {
                    if (!visited[i]) {
                        stack.addLast(i);

                        visited[i] = true;
                    }
                }
            }
        }
    }
}