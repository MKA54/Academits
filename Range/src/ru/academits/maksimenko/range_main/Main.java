package ru.academits.maksimenko.range_main;

import ru.academits.maksimenko.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(4, 9);
        Range range2 = new Range(2, 39);

        Range intersection = range1.getIntersection(range2);
        System.out.println("Интервал пересечения: " + intersection);

        Range[] merger = range1.getMerger(range2);
        System.out.println("Объединение интервалов: " + Arrays.toString(merger));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Раность интервалов: " + Arrays.toString(difference));
    }
}