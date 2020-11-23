package ru.academits.maksimenko.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private int[][] graph;

    public Graph(int[][] graph) {
        if (graph.length == 0) {
            throw new IllegalArgumentException("The count of rows in the array must be greater than 0, the current number of rows: " + graph.length);
        }

        if (graph.length != graph[0].length) {
            throw new IllegalArgumentException("The matrix is not square " + graph.length + "x" + graph[0].length);
        }

        int maxSize = 0;

        for (int[] array : graph) {
            if (maxSize < array.length) {
                maxSize = array.length;
            }
        }

        this.graph = new int[graph.length][maxSize];

        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = Arrays.copyOf(graph[i], maxSize);
        }
    }

    public void visitInWidth() {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        visited[0] = true;

        IntConsumer print = System.out::println;

        while (!queue.isEmpty()) {
            int top = queue.remove();

            print.accept(top);

            for (int i = 0; i < graph[top].length; i++) {
                if (graph[top][i] == 1 && !visited[i]) {
                    queue.add(i);

                    visited[i] = true;
                }
            }

            if (queue.isEmpty()) {
                for (int i = 0; i < visited.length; i++) {
                    if (!visited[i]) {
                        queue.add(i);

                        visited[i] = true;
                    }
                }
            }
        }
    }

    public void visitInDepth() {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        visited[0] = true;

        IntConsumer print = System.out::println;

        while (!stack.isEmpty()) {
            int top = stack.removeLast();

            print.accept(top);

            for (int i = 0; i < graph[top].length; i++) {
                if (graph[top][i] == 1 && !visited[i]) {
                    stack.addLast(i);

                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = visited.length - 1; i >= 0; i--) {
                    if (!visited[i]) {
                        stack.addLast(i);

                        visited[i] = true;
                    }
                }
            }
        }
    }

    public void visitInDepthRecursion() {
        IntConsumer print = System.out::println;

        boolean[] visited = new boolean[graph.length];

        Deque<Integer> stack = new LinkedList<>();

        visit(0, print, visited, stack);
    }

    private void visit(int top, IntConsumer print, boolean[] visited, Deque<Integer> stack) {
        if (visited[top]) {
            return;
        }

        stack.addLast(top);

        visited[top] = true;

        print.accept(top);

        for (int i = graph[top].length - 1; i >= 0; i--) {
            if (graph[top][i] == 1 && !visited[i]) {
                visit(i, print, visited, stack);
            }
        }

        stack.removeLast();

        if (stack.isEmpty()) {
            for (int i = 0; i < graph[top].length; i++) {
                if (!visited[i]) {
                    visit(i, print, visited, stack);
                }
            }
        }
    }
}