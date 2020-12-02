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

        for (int[] arrays : graph) {
            if (graph.length != arrays.length) {
                throw new IllegalArgumentException("The matrix is not square " + graph.length + "x" + arrays.length);
            }
        }

        this.graph = new int[graph.length][graph[0].length];

        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = Arrays.copyOf(graph[i], graph[0].length);
        }
    }

    public void visitInWidth(IntConsumer consumer) {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        visited[0] = true;

        while (!queue.isEmpty()) {
            int top = queue.remove();

            consumer.accept(top);

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

                        break;
                    }
                }
            }
        }
    }

    public void visitInDepth(IntConsumer consumer) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        visited[0] = true;

        while (!stack.isEmpty()) {
            int top = stack.removeLast();

            consumer.accept(top);

            for (int i = graph[top].length - 1; i >= 0; i--) {
                if (graph[top][i] == 1 && !visited[i]) {
                    stack.addFirst(i);

                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = 0; i < graph[top].length; i++) {
                    if (!visited[i]) {
                        stack.addLast(i);

                        visited[i] = true;

                        break;
                    }
                }
            }
        }
    }

    public void visitInDepthRecursion(IntConsumer consumer) {
        visit(0, consumer, new boolean[graph.length]);
    }

    private void visit(int top, IntConsumer consumer, boolean[] visited) {
        if (visited[top]) {
            return;
        }

        visited[top] = true;

        consumer.accept(top);

        for (int i = graph[top].length - 1; i >= 0; i--) {
            if (graph[top][i] == 1 && !visited[i]) {
                visit(i, consumer, visited);
            }
        }

        for (int i = 0; i < graph[top].length; i++) {
            if (!visited[i]) {
                visit(i, consumer, visited);
            }
        }
    }
}