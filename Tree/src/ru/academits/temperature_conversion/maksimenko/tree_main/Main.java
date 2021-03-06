package ru.academits.temperature_conversion.maksimenko.tree_main;

import ru.academits.temperature_conversion.maksimenko.tree.Tree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbers1 = new Tree<>();

        numbers1.add(15);
        numbers1.add(13);
        numbers1.add(28);
        numbers1.add(11);
        numbers1.add(16);
        numbers1.add(25);
        numbers1.add(31);
        numbers1.add(9);
        numbers1.add(15);
        numbers1.add(29);
        numbers1.add(35);
        numbers1.add(12);
        numbers1.add(30);

        System.out.println("Количество элементов в дереве: " + numbers1.getSize());

        boolean contains = numbers1.contains(55);
        System.out.println("Наличие числа в дереве: " + contains);

        boolean remove = numbers1.remove(29);
        System.out.println("Проверка на удаление числа из дереве: " + remove);

        System.out.println("Количество элементов в дереве: " + numbers1.getSize());

        System.out.println("Обход дерева в глубину с рекурсией:");
        numbers1.visitInDepthRecursion(System.out::println);
        System.out.println();

        System.out.println("Обход дерева в глубину:");
        numbers1.visitInDepth(System.out::println);
        System.out.println();

        System.out.println("Обход дерева в ширину:");
        numbers1.visitInWidth(System.out::println);
        System.out.println();

        Tree<Integer> numbers2 = new Tree<>(Comparator.reverseOrder());

        numbers2.add(15);
        numbers2.add(17);
        numbers2.add(8);
        numbers2.add(7);

        System.out.println("Обход дерева в ширину:");
        numbers2.visitInWidth(System.out::println);
    }
}