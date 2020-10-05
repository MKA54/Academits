package ru.academits.maksimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("dimension: " + dimension + " <= 0");
        }

        coordinates = new double[dimension];
    }

    public Vector(Vector vector) {
        this(vector.coordinates);
    }

    public Vector(double[] coordinates) {
        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int dimension, double[] coordinates) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("dimension: " + dimension + " <= 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, Math.max(coordinates.length, dimension));
    }

    public int getSize() {
        return coordinates.length;
    }

    public void add(Vector vector) {
        if (vector.getSize() > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }


    public void subtract(Vector vector) {
        if (vector.getSize() > coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= -1;
        }
    }

    public double getLength() {
        int length = 0;

        for (double coordinate : coordinates) {
            length += Math.pow(coordinate, 2);
        }

        return Math.sqrt(length);
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds for length " + coordinates.length);
        }

        if (index > coordinates.length) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds for length " + coordinates.length);
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

    public static Vector addVectors(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.add(vector2);
        return result;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);
        return result;
    }

    public static double scalarProductVectors(Vector vector1, Vector vector2) {
        double result = 0;

        int length = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < length; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }

    @Override
    public String toString() {
        if (coordinates.length == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{ ");

        for (Double c : coordinates) {
            stringBuilder.append(c).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
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