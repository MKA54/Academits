package ru.academits.maksimenko.shape_main;

import ru.academits.maksimenko.shape.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape square = new Square(3);

        System.out.printf("Площадь квадрата: %.2f%n", square.getArea());
        System.out.printf("Периметр квадрата: %.2f%n", square.getPerimeter());
        System.out.println();

        Shape triangle = new Triangle(11, 15, 14, 8, 6, 9);

        System.out.printf("Площадь треугольника: %.2f%n", triangle.getArea());
        System.out.printf("Периметр треугольника: %.2f%n", triangle.getPerimeter());
        System.out.println();

        Shape rectangle = new Rectangle(4, 15);

        System.out.printf("Площадь прямоугольника: %.2f%n", rectangle.getArea());
        System.out.printf("Периметр прямоугольника: %.2f%n", rectangle.getPerimeter());
        System.out.println();

        Shape circle = new Circle(15);

        System.out.printf("Площадь окружности: %.2f%n", circle.getArea());
        System.out.printf("Периметр окружности: %.2f%n", circle.getPerimeter());
        System.out.println();

        Shape[] shapesArray = {
                new Square(5),
                new Square(8),
                new Rectangle(16, 4),
                new Triangle(4, 15, 18, 2, 7, 9),
                new Circle(8),
                new Circle(12),
                new Triangle(8, 7, 18, 2, 17, 6),
                new Square(22),
                new Rectangle(22, 8),
                new Circle(22),
                new Square(24)
        };

        printShapeWithMaxArea(shapesArray);

        printShapeWithSecondLargestPerimeter(shapesArray);
    }

    public static void printShapeWithMaxArea(Shape[] shapesArray) {
        Arrays.sort(shapesArray, new AreaComparator().reversed());

        System.out.println("Фигура с максимальной площадью: " + shapesArray[0]);
    }

    public static void printShapeWithSecondLargestPerimeter(Shape[] shapesArray) {
        Arrays.sort(shapesArray, new PerimeterComparator().reversed());

        System.out.println("Фигура со вторым по величине периметром: " + shapesArray[1]);
    }
}