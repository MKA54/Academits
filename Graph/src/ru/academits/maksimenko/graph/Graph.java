package ru.academits.maksimenko.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] matrix;

    public Graph(int[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("The count of rows in the array must be greater than 0, the current number of rows: " + matrix.length);
        }

        for (int[] array : matrix) {
            if (matrix.length != array.length) {
                throw new IllegalArgumentException("The matrix is not square " + matrix.length + "x" + array.length);
            }
        }

        this.matrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], matrix[0].length);
        }
    }

    public void visitInWidth(IntConsumer consumer) {
        boolean[] visited = new boolean[matrix.length];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.remove();

            consumer.accept(currentVertex);

            for (int i = 0; i < matrix[currentVertex].length; i++) {
                if (matrix[currentVertex][i] == 1 && !visited[i]) {
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
        boolean[] visited = new boolean[matrix.length];

        Deque<Integer> stack = new LinkedList<>();

        stack.addLast(0);

        visited[0] = true;

        while (!stack.isEmpty()) {
            int currentVertex = stack.removeLast();

            consumer.accept(currentVertex);

            for (int i = 0; i < matrix[currentVertex].length; i++) {
                if (matrix[currentVertex][i] == 1 && !visited[i]) {
                    stack.addLast(i);

                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = 0; i < matrix[currentVertex].length; i++) {
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
        visit(0, consumer, new boolean[matrix.length]);
    }

    private void visit(int currentVertex, IntConsumer consumer, boolean[] visited) {
        if (visited[currentVertex]) {
            return;
        }

        visited[currentVertex] = true;

        consumer.accept(currentVertex);

        for (int i = 0; i < matrix[currentVertex].length; i++) {
            if (matrix[currentVertex][i] == 1 && !visited[i]) {
                visit(i, consumer, visited);
            }
        }

        for (int i = 0; i < matrix[currentVertex].length; i++) {
            if (!visited[i]) {
                visit(i, consumer, visited);
            }
        }
    }
}