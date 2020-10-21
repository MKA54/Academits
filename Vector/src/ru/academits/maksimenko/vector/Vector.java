package ru.academits.maksimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size: " + size + " <= 0");
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.coordinates);
    }

    public Vector(double[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("invalid array length: " + coordinates.length);
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("size: " + size + " <= 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    public void add(Vector vector) {
        if (vector.coordinates.length > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.getSize(); i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.coordinates.length > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.getSize(); i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double coordinate : coordinates) {
            sum += Math.pow(coordinate, 2);
        }

        return Math.sqrt(sum);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Limits of acceptable values from 0, to  " + coordinates.length
                    + " entered: " + index);
        }
    }

    public double getCoordinateByIndex(int index) {
        checkIndex(index);

        return coordinates[index];
    }

    public void setCoordinateByIndex(int index, double coordinate) {
        checkIndex(index);

        coordinates[index] = coordinate;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int length = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < length; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double c : coordinates) {
            stringBuilder.append(c).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        return Arrays.equals(coordinates, v.coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        return prime * hash + Arrays.hashCode(coordinates);
    }
}