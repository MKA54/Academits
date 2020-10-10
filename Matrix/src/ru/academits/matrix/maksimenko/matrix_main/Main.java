package ru.academits.matrix.maksimenko.matrix_main;

import ru.academits.maksimenko.vector.Vector;
import ru.academits.matrix.maksimenko.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 5);
        System.out.println("Матрица нулей: " + matrix1);

        double[][] matrix = new double[4][9];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0, number = 1; j < matrix[i].length; j++, number++) {
                matrix[i][j] = number;
            }
        }

        Matrix matrix2 = new Matrix(matrix);
        System.out.println("Матрица из двумерного массива: " + matrix2);

        Vector[] vectors1 = new Vector[]{new Vector(new double[]{1, 22, 33, 44}), new Vector(new double[]{2, 15, 16, 17}),
                new Vector(new double[]{33, 44, 18, 19}), new Vector(new double[]{5, 6, 7, 8})};

        Matrix matrix3 = new Matrix(vectors1);
        System.out.println("Матрица из массива вектров-строк: " + matrix3);

        Matrix matrix4 = new Matrix(matrix3);
        System.out.println("Скопированная матрица: " + matrix4);

        int length = matrix2.getSize();
        System.out.println("Размерность матрицы: " + length);

        Vector vector1 = new Vector(new double[]{11, 15, 13, 15, 16});

        matrix1.setLinesVector(1, vector1);
        System.out.println("Матрица после записи в неё вектора по индексу: " + matrix1);

        Vector vector2 = matrix3.getColumnVector(1);
        System.out.println("Вектор-столбец полученный по индексу: " + vector2);

        Vector vector3 = matrix3.getLinesVector(1);
        System.out.println("Вектор-строка полученная по индексу: " + vector3);

        Vector[] vectors2 = new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{5, 66, 7}),
                new Vector(new double[]{9, 101, 11})};

        Matrix matrix5 = new Matrix(vectors2);
        System.out.println("Матрица перед транспонированием: " + matrix5);

        double matrixDeterminant = matrix5.getMatrixDeterminant();
        System.out.println("Определитель матрицы: " + matrixDeterminant);

        matrix5.transpose();

        System.out.println("Матрица после транспонирования: " + matrix5);

        System.out.println("Матрица до умножения на скаляр: " + matrix3);

        matrix3.multiplyByScalar(2);

        System.out.println("Матрица после умножения на скаляр: " + matrix3);

        Vector[] vectors3 = new Vector[]{new Vector(new double[]{2, 4, 0}), new Vector(new double[]{-2, 1, 3}),
                new Vector(new double[]{-1, 0, 1})};

        Matrix matrix6 = new Matrix(vectors3);

        Vector vector4 = new Vector(new double[]{1, 2, -1});

        Vector vector5 = matrix6.multiplyByVector(vector4);
        System.out.println("Результат умножения матрицы на вектор: " + vector5);

        matrix5.add(matrix6);
        System.out.println("Результат прибавления матрицы: " + matrix5);

        matrix6.subtract(matrix5);
        System.out.println("Результат вычитания матрицы: " + matrix6);

        Matrix matrix7 = Matrix.addMatrices(matrix6, matrix5);
        System.out.println("Результат сложения матриц: " + matrix7);

        Matrix matrix8 = Matrix.subtractMatrices(matrix7, matrix5);
        System.out.println("Результат разности матриц: " + matrix8);

        Vector[] vectors4 = new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{4, 5, 6}),
                new Vector(new double[]{7, 8, 9})};

        Matrix matrix9 = new Matrix(vectors4);

        Vector[] vectors5 = new Vector[]{new Vector(new double[]{3, -4, 5}), new Vector(new double[]{1, -1, 1}),
                new Vector(new double[]{2, -2, 3})};

        Matrix matrix10 = new Matrix(vectors5);

        Matrix matrix11 = Matrix.multiplicationMatrix(matrix9, matrix10);
        System.out.println("Результат умножения матриц: " + matrix11);
    }
}