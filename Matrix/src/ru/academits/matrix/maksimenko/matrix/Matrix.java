package ru.academits.matrix.maksimenko.matrix;

import ru.academits.maksimenko.vector.Vector;

public class Matrix {
    private Vector[] vectorsArray;

    public Matrix(int linesCount, int columnsDimension) {
        vectorsArray = new Vector[linesCount];
        for (int i = 0; i < linesCount; i++) {
            vectorsArray[i] = new Vector(columnsDimension);
        }
    }

    public Matrix(double[][] array) {
        vectorsArray = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            vectorsArray[i] = new Vector(array[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.vectorsArray);
    }

    public Matrix(Vector[] vector) {
        int maxLength = 0;

        for (Vector v : vector) {
            if (v == null) {
                continue;
            }

            if (maxLength < v.getSize()) {
                maxLength = v.getSize();
            }
        }

        vectorsArray = new Vector[vector.length];

        for (int i = 0; i < vector.length; i++) {
            if (vector[i].getSize() < maxLength) {
                Vector temp = new Vector(maxLength);

                vector[i].add(temp);
            }

            vectorsArray[i] = new Vector(vector[i]);
        }
    }

    public int getSize() {
        return vectorsArray.length * vectorsArray[0].getSize();
    }

    public Vector getLinesVector(int index) {
        checkIndex(index);

        return vectorsArray[index];
    }

    public void setLinesVector(int index, Vector vector) {
        checkIndex(index);

        if (vector.getSize() < vectorsArray[index].getSize()) {
            throw new IllegalArgumentException("The size of the vector is smaller than the current one: " +
                    vector.getSize() + " < " + vectorsArray[index].getSize());
        }

        if (vector.getSize() > vectorsArray[index].getSize()) {
            throw new IllegalArgumentException("The vector size is larger than the current one: " + vector.getSize() +
                    " > " + vectorsArray[index].getSize());
        }

        vectorsArray[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index >= vectorsArray[0].getSize()) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds for length " + vectorsArray[0].getSize());
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("the index " + index + " has a negative value");
        }

        double[] coordinates = new double[vectorsArray.length];

        int i = 0;

        for (Vector v : vectorsArray) {
            coordinates[i] = v.getCoordinateByIndex(index);

            i++;
        }

        return new Vector(coordinates);
    }

    public void transpose() {
        Vector[] newVector = new Vector[vectorsArray[0].getSize()];

        for (int i = 0; i < newVector.length; i++) {
            newVector[i] = new Vector(getColumnVector(i));
        }

        vectorsArray = newVector;
    }

    public void multiplyByScalar(int scalar) {
        for (Vector vector : vectorsArray) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getMatrixDeterminant() {
        double result = 0;

        int length = vectorsArray[0].getSize();
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

                productSideDiagonal *= vectorsArray[h].getCoordinateByIndex(n);
                productMainDiagonal *= vectorsArray[h].getCoordinateByIndex(k);
            }

            result += productMainDiagonal - productSideDiagonal;

            i++;
            j--;
        }

        return result;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() > vectorsArray[0].getSize()) {
            throw new RuntimeException("The dimension of the vector is greater than the length of the matrix: vector "
                    + vector.getSize() + " matrix " + vectorsArray.length);
        }

        if (vector.getSize() < vectorsArray[0].getSize()) {
            throw new RuntimeException("The dimension of the vector is less than the length of the matrix: vector "
                    + vector.getSize() + " matrix " + vectorsArray.length);
        }

        double[] result = new double[vector.getSize()];

        for (int i = 0; i < result.length; i++) {
            double value = 0;

            for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                value += vector.getCoordinateByIndex(j) * vectorsArray[i].getCoordinateByIndex(j);
            }

            result[i] = value;
        }

        return new Vector(result);
    }

    public void add(Matrix matrix) {
        for (int i = 0; i < vectorsArray.length; i++) {
            vectorsArray[i] = Vector.addVectors(vectorsArray[i], matrix.vectorsArray[i]);
        }
    }

    public void subtract(Matrix matrix) {
        for (int i = 0; i < vectorsArray.length; i++) {
            vectorsArray[i] = Vector.subtractVectors(vectorsArray[i], matrix.vectorsArray[i]);
        }
    }

    private static void checkMatricesForDimension(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectorsArray.length != matrix2.vectorsArray.length ||
                matrix1.vectorsArray[0].getSize() != matrix2.vectorsArray[0].getSize()) {
            throw new RuntimeException("matrices of different order: matrix1 " + matrix1.vectorsArray[0].getSize() +
                    "x" + matrix1.vectorsArray.length + ", matrix2 " + matrix2.vectorsArray[0].getSize() + "x" +
                    matrix2.vectorsArray.length);
        }
    }

    public static Matrix addMatrices(Matrix matrix1, Matrix matrix2) {
        checkMatricesForDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);

        result.add(matrix2);

        return result;
    }

    public static Matrix subtractMatrices(Matrix matrix1, Matrix matrix2) {
        checkMatricesForDimension(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }

    public static Matrix multiplicationMatrix(Matrix matrix1, Matrix matrix2) {
        checkMatricesForDimension(matrix1, matrix2);

        Vector[] result = new Vector[matrix1.vectorsArray.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = new Vector(matrix1.multiplyByVector(matrix2.getColumnVector(i)));
        }

        return new Matrix(result);
    }

    @Override
    public String toString() {
        if (vectorsArray.length == 0) {
            return "{ }";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{ ");

        for (Vector v : vectorsArray) {
            stringBuilder.append(v).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append(" }");

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds for length " + vectorsArray.length);
        }

        if (index >= vectorsArray.length) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds for length " + vectorsArray.length);
        }
    }
}