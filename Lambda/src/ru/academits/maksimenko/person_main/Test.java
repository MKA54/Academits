package ru.academits.maksimenko.person_main;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();

        System.out.print("Сколько элементов вычислить: ");
        int elementsCount = scanner.nextInt();

        DoubleStream roots = DoubleStream.iterate(number, Math::sqrt)
                .limit(elementsCount);

        roots.forEach(System.out::println);

        System.out.println("Числа Фибоначчи: ");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .map(f -> f[0])
                .limit(10)
                .forEach(System.out::println);
    }
}