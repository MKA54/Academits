package ru.academits.maksimenko.tree_main;

import ru.academits.maksimenko.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbers = new Tree<>(15);

        numbers.add(13);
        numbers.add(28);
        numbers.add(11);
        numbers.add(16);
        numbers.add(25);
        numbers.add(31);
        numbers.add(9);
        numbers.add(15);
        numbers.add(29);
        numbers.add(35);
        numbers.add(12);
        numbers.add(30);

        System.out.println("Количество элементов в дереве: " + numbers.getSize());

        boolean contains = numbers.contains(25);
        System.out.println("Наличие числа в дереве: " + contains);

        boolean remove = numbers.remove(45);
        System.out.println("Проверка на удаление числа из дереве: " + remove);

        System.out.println("Количество элементов в дереве: " + numbers.getSize());

        System.out.println("Обход дерева в глубину с рекурсией:");
        numbers.depthInVisitRecursion(numbers.getRoot());
        System.out.println();

        System.out.println("Обход дерева в глубину:");
        numbers.depthInVisit();
        System.out.println();

        System.out.println("Обход дерева в ширину:");
        numbers.widthInVisit();
    }
}