package ru.academits.maksimenko.range_main;

import ru.academits.maksimenko.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(4, 9);
        Range range2 = new Range(2, 39);

        Range intersection = range1.getIntersection(range2);
        System.out.println("Интервал пересечения: " + intersection);

        Range[] merger = range1.getUnion(range2);
        System.out.println("Объединение интервалов: " + Arrays.toString(merger));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Раность интервалов: " + Arrays.toString(difference));

        Scanner scanner = new Scanner(System.in);

        Range range3 = new Range(5.5, 7.7);

        System.out.printf("Длина диапазона: %.2f%n", range3.getLength());

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();

        System.out.println("Проверка на нахождения числа в диапазоне: " + range3.isInside(number));

        range3.setFrom(18.5);
        range3.setTo(23.3);

        System.out.printf("Длина диапазона: %.2f%n", range3.getLength());
        System.out.println("Диапазон: " + range3);
    }
}