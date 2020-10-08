package ru.academits.maksimenko.vector_main;

import ru.academits.maksimenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(1);
        System.out.println(vector1);

        double[] coordinates1 = new double[]{2.0, 3.0, 4.0, 5.0, 6.0};
        Vector vector2 = new Vector(coordinates1);
        System.out.println(vector2);

        Vector vector3 = new Vector(6, coordinates1);
        System.out.println(vector3);

        Vector vector4 = new Vector(vector2);
        System.out.println(vector4);

        System.out.println("Размерность вектора: " + vector4.getSize());

        vector2.add(vector4);
        System.out.println("Вектор после прибовления к нему другого вектора: " + vector2);

        vector2.subtract(vector4);
        System.out.println("Вектор после вычитания из него другого ветора: " + vector2);

        vector2.multiplyByScalar(2);
        System.out.println("Результат умножения вектора на скаляр: " + vector2);

        vector4.reverse();
        System.out.println("Разворот вектора: " + vector4);

        double vectorLength = vector2.getLength();
        System.out.printf("Длина ветктора: %.2f%n", vectorLength);

        double coordinate = vector2.getCoordinateByIndex(2);
        System.out.println("Полученная координата по индексу: " + coordinate);

        vector2.setCoordinateByIndex(3, coordinate);
        System.out.println("!!!!!!!!!! " + vector2);

        double[] coordinates2 = new double[]{1, 2, 3, 4, 5};
        Vector vector5 = new Vector(coordinates2);

        double[] coordinates3 = new double[]{1, 2, 3, 4, 5};
        Vector vector6 = new Vector(coordinates3);

        double scalarProductVectors = Vector.scalarProductVectors(vector5, vector6);
        System.out.println("Скалярное произведение векторов: " + scalarProductVectors);

        Vector vector8 = Vector.addVectors(vector5, vector6);
        System.out.println(vector8);

        Vector vector9 = Vector.subtractVectors(vector5, vector6);
        System.out.println(vector9);
    }
}