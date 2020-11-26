package ru.academits.maksimenko.matrix;

import ru.academits.maksimenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("The count of rows must be greater than 0, the value entered: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("The count of columns must be greater than 0, the value entered: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The count of rows in the array must be greater than 0, the current number of rows: " + array.length);
        }

        rows = new Vector[array.length];

        int maxSize = 0;

        for (double[] doubles : array) {
            if (maxSize < doubles.length) {
                maxSize = doubles.length;
            }
        }

        for (int j = 0; j < array.length; j++) {
            rows[j] = new Vector(maxSize, array[j]);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("The array size must be greater than 0, the current size: " + vectorsArray.length);
        }

        int maxSize = 0;

        for (Vector v : vectorsArray) {
            if (v == null) {
                continue;
            }

            if (maxSize < v.getSize()) {
                maxSize = v.getSize();
            }
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            if (vectorsArray[i].getSize() < maxSize) {
                rows[i] = Vector.getSum(vectorsArray[i], new Vector(new double[maxSize]));

                continue;
            }

            rows[i] = new Vector(vectorsArray[i]);
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (vector.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException("The size of the vector is not equal to the number of columns in the" +
                    " matrix: vector " + vector.getSize() + ", columnsCount " + rows[index].getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (getColumnsCount() - 1) + ". Index = " + index);
        }

        Vector vector = new Vector(rows.length);

        int i = 0;

        for (Vector v : rows) {
            vector.setCoordinateByIndex(i, v.getCoordinateByIndex(index));

            i++;
        }

        return vector;
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < newRows.length; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("The matrix is not square: " + rows.length + "x" + getColumnsCount());
        }

        int orderNumber = rows.length;

        if (orderNumber == 1) {
            return rows[0].getCoordinateByIndex(0);
        }

        double determinant = 0;
        Matrix minor = new Matrix(orderNumber - 1, orderNumber - 1);
        int additionalSign = 1;

        for (int i = 0; i < orderNumber; i++) {
            int x = 0;
            int y = 0;

            for (int j = 1; j < orderNumber; j++) {
                for (int k = 0; k < orderNumber; k++) {
                    if (i == k) {
                        continue;
                    }

                    minor.rows[x].setCoordinateByIndex(y, rows[j].getCoordinateByIndex(k));

                    ++y;

                    if (y == orderNumber - 1) {
                        y = 0;

                        ++x;
                    }
                }
            }

            determinant += additionalSign * rows[0].getCoordinateByIndex(i) * minor.getDeterminant();

            additionalSign *= -1;
        }

        return determinant;
    }

    public Vector getProduct(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("The size of the vector is not equal to the count of columns in the matrix: vector "
                    + vector.getSize() + ", matrix " + getColumnsCount());
        }

        Vector result = new Vector(vector.getSize());

        for (int i = 0; i < result.getSize(); i++) {
            result.setCoordinateByIndex(i, Vector.getScalarProduct(vector, rows[i]));
        }

        return result;
    }

    private static void checkMatricesForDimension(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() ||
                matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices of different order: matrix1 " + matrix1.getRowsCount() +
                    "x" + matrix1.getColumnsCount() + ", matrix2 " + matrix2.getRowsCount() + "x" + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatricesForDimension(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesForDimension(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesForDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);

        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesForDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("The number of columns in matrix1 " + matrix1.getColumnsCount() +
                    " is not equal to the number of rows in matrix2 " + matrix2.getRowsCount());
        }

        Matrix result = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double coordinate = 0;

                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    coordinate += matrix1.rows[i].getCoordinateByIndex(k) * matrix2.rows[k].getCoordinateByIndex(j);
                }

                result.rows[i].setCoordinateByIndex(j, coordinate);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector v : rows) {
            stringBuilder.append(v).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (rows.length - 1) + ". Index = " + index);
        }
    }
}