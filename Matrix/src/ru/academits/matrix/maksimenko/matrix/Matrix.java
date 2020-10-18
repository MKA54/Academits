package ru.academits.matrix.maksimenko.matrix;

import ru.academits.maksimenko.vector.Vector;

public class Matrix {
    private Vector[] vectorsMatrix;

    public Matrix(int row, int columnsCount) {
        vectorsMatrix = new Vector[row];

        for (int i = 0; i < row; i++) {
            vectorsMatrix[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array[0].length == 0) {
            throw new IllegalArgumentException("Invalid number of columns: " + array[0].length);
        }

        vectorsMatrix = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            vectorsMatrix[i] = new Vector(array[i]);
        }
    }

    public Matrix(Matrix vectorsMatrix) {
        this(vectorsMatrix.vectorsMatrix);
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Invalid array length: " + vectorsArray.length);
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

        vectorsMatrix = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            if (vectorsArray[i].getSize() < maxSize) {
                vectorsArray[i].add(new Vector(maxSize));
            }

            vectorsMatrix[i] = new Vector(vectorsArray[i]);
        }
    }

    public int getColumnsCount() {
        return vectorsMatrix[0].getSize();
    }

    public int getRowsCount() {
        return vectorsMatrix.length;
    }

    public Vector getLine(int index) {
        checkRowIndex(index);

        return vectorsMatrix[index];
    }

    public void setLine(int index, Vector vector) {
        if (vector.getSize() != vectorsMatrix[index].getSize()) {
            throw new IllegalArgumentException("The size of the vector is smaller than the current one: " +
                    vector.getSize() + " < " + vectorsMatrix[index].getSize());
        }

        vectorsMatrix[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index >= vectorsMatrix[0].getSize()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + vectorsMatrix[0].getSize());
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("The index " + index + " has a negative value");
        }

        Vector vector = new Vector(vectorsMatrix.length);

        int i = 0;

        for (Vector v : vectorsMatrix) {
            vector.setCoordinateByIndex(i, v.getCoordinateByIndex(index));

            i++;
        }

        return vector;
    }

    public void transpose() {
        Vector[] newVectorsMatrix = new Vector[vectorsMatrix[0].getSize()];

        for (int i = 0; i < newVectorsMatrix.length; i++) {
            newVectorsMatrix[i] = new Vector(getColumnVector(i));
        }

        vectorsMatrix = newVectorsMatrix;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : vectorsMatrix) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (vectorsMatrix.length != vectorsMatrix[0].getSize()) {
            throw new IllegalArgumentException("");
        }

        double result = 0;

        int length = vectorsMatrix[0].getSize();
        int i = 0;
        int j = length - 1;

        while (i < length) {
            double productMainDiagonal = 1;
            double productSideDiagonal = 1;

            for (int m = 0, h = 0, k = i, n = j; m < length; m++, h++, k++, n--) {
                if (k == length) {
                    k = 0;
                }

                if (n < 0) {
                    n = length - 1;
                }

                productSideDiagonal *= vectorsMatrix[h].getCoordinateByIndex(n);
                productMainDiagonal *= vectorsMatrix[h].getCoordinateByIndex(k);
            }

            result += productMainDiagonal - productSideDiagonal;

            i++;
            j--;
        }

        return result;
    }

    public Vector getProductOnVector(Vector vector) {
        if (vector.getSize() != vectorsMatrix[0].getSize()) {
            throw new IllegalArgumentException("The dimension of the vector is not equal to the dimension of the matrix: vector "
                    + vector.getSize() + " matrix " + vectorsMatrix.length);
        }

        Vector result = new Vector(vector.getSize());

        int i = 0;

        for (Vector v : vectorsMatrix) {
            result.setCoordinateByIndex(i, Vector.getScalarProduct(vector, v));

            i++;
        }

        return result;
    }

    private static void checkMatricesForDimension(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectorsMatrix.length != matrix2.vectorsMatrix.length ||
                matrix1.vectorsMatrix[0].getSize() != matrix2.vectorsMatrix[0].getSize()) {
            throw new IllegalArgumentException("Matrices of different order: matrix1 " + matrix1.vectorsMatrix[0].getSize() +
                    "x" + matrix1.vectorsMatrix.length + ", matrix2 " + matrix2.vectorsMatrix[0].getSize() + "x" +
                    matrix2.vectorsMatrix.length);
        }
    }

    public void add(Matrix matrix) {
        checkMatricesForDimension(this, matrix);

        for (int i = 0; i < this.vectorsMatrix.length; i++) {
            this.vectorsMatrix[i].add(matrix.vectorsMatrix[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesForDimension(this, matrix);

        for (int i = 0; i < this.vectorsMatrix.length; i++) {
            this.vectorsMatrix[i].subtract(matrix.vectorsMatrix[i]);
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
        checkMatricesForDimension(matrix1, matrix2);

        Vector[] vectors = new Vector[matrix1.vectorsMatrix.length];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(matrix1.getProductOnVector(matrix2.getColumnVector(i)));
        }

        return new Matrix(vectors);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector v : vectorsMatrix) {
            stringBuilder.append(v).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= vectorsMatrix.length) {
            throw new IndexOutOfBoundsException("limits of acceptable values from 0" + ", to " + vectorsMatrix.length
                    + " entered: " + index);
        }
    }
}