package ru.academits.maksimenko.matrix;

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
            throw new IllegalArgumentException("Invalid count of columns: " + array[0].length);
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
            throw new IllegalArgumentException("The size of the vector is not equal to the number of columns in the" +
                    " matrix: vector " + vector.getSize() + ", columnsCount " + vectorsMatrix[index].getSize());
        }

        vectorsMatrix[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index >= vectorsMatrix[0].getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Index must be from 0 to " + vectorsMatrix[0].getSize() + ". Index = " + index);
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
        Vector[] newVectorsArray = new Vector[vectorsMatrix[0].getSize()];

        for (int i = 0; i < newVectorsArray.length; i++) {
            newVectorsArray[i] = new Vector(getColumnVector(i));
        }

        vectorsMatrix = newVectorsArray;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : vectorsMatrix) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (vectorsMatrix.length != vectorsMatrix[0].getSize()) {
            throw new ArithmeticException("The matrix is not square: " + vectorsMatrix.length + "x"
                    + vectorsMatrix[0].getSize());
        }

        if (vectorsMatrix.length == 1 && vectorsMatrix[0].getSize() == 1) {
            return vectorsMatrix[0].getCoordinateByIndex(0);
        }

        if (vectorsMatrix.length == 2 && vectorsMatrix[0].getSize() == 2) {
            return vectorsMatrix[0].getCoordinateByIndex(0) * vectorsMatrix[1].getCoordinateByIndex(1)
                    - vectorsMatrix[0].getCoordinateByIndex(1) * vectorsMatrix[1].getCoordinateByIndex(0);
        }

        double result = 0;

        int minorSize = vectorsMatrix[0].getSize() - 1;
        int length = vectorsMatrix[0].getSize();

        for (int x = 0, j = 1; x < length; x++, j++) {
            double value = vectorsMatrix[0].getCoordinateByIndex(x);
            double complement = 0;

            if (value != 0) {
                int h = 0;

                int r = x == 0 ? 1 : 0;
                int t = x == minorSize ? minorSize - 1 : minorSize;

                while (h < minorSize) {
                    double mainLineProduct = 1;
                    double sideLineProduct = 1;

                    if (r == x) {
                        ++r;
                    }

                    if (t == x) {
                        --t;
                    }

                    for (int n = 0, k = 1, m = r, p = t; n < minorSize;
                         n++, k++, m++, p--) {

                        if (m == x) {
                            ++m;
                        }

                        if (m >= length) {
                            m = x == 0 ? 1 : 0;
                        }

                        if (p == x) {
                            --p;
                        }

                        if (p < 0) {
                            p = x == minorSize ? minorSize - 1 : minorSize;
                        }

                        mainLineProduct *= vectorsMatrix[k].getCoordinateByIndex(m);
                        sideLineProduct *= vectorsMatrix[k].getCoordinateByIndex(p);
                    }

                    complement += mainLineProduct - sideLineProduct;

                    ++r;
                    --t;

                    ++h;
                }
            }

            result += Math.pow(-1, 1 + j) * value * complement;
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
            throw new IllegalArgumentException("Matrices of different order: matrix1 " + matrix1.vectorsMatrix.length +
                    "x" + matrix1.vectorsMatrix[0].getSize() + ", matrix2 " + matrix2.vectorsMatrix.length + "x" +
                    matrix2.vectorsMatrix[0].getSize());
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
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (vectorsMatrix.length - 1) + ". Index = " + index);
        }
    }
}