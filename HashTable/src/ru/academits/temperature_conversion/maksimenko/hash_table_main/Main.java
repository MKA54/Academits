package ru.academits.temperature_conversion.maksimenko.hash_table_main;

import ru.academits.temperature_conversion.maksimenko.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<String> namesTable = new HashTable<>(15);

        namesTable.add("Петр");
        namesTable.add("Сергей");
        namesTable.add("Владимир");
        namesTable.add("Виктор");
        namesTable.add("Генадий");
        namesTable.add("Константин");

        List<String> namesList = new ArrayList<>(Arrays.asList("Глеб", "Павел", "Герман"));

        namesTable.addAll(namesList);

        System.out.println("Содержимое хэш-таблицы: " + namesTable);

        System.out.println("Количество элементов в хэш-таблице: " + namesTable.size());

        boolean contains = namesTable.contains("Герман");
        System.out.println("Наличие элемента в хэш-таблице: " + contains);

        Object[] objects = namesTable.toArray();
        System.out.println("Массив объектов: " + Arrays.toString(objects));

        String[] namesArray = namesTable.toArray(new String[0]);
        System.out.println("Массив имён: " + Arrays.toString(namesArray));

        List<String> studentsList = new ArrayList<>(Arrays.asList("Роман", "Виктор", "Генадий"));

        contains = namesTable.containsAll(studentsList);
        System.out.println("Наличие всех студентов в таблице: " + contains);

        boolean isRemoved = namesTable.removeAll(studentsList);
        System.out.println("Проверка на удаление элементов в таблице: " + isRemoved);

        List<String> nonInternsList = new ArrayList<>(Arrays.asList("Глеб", "Павел", "Сергей"));

        isRemoved = namesTable.retainAll(nonInternsList);
        System.out.println("Проверка на удаление элементов в таблице: " + isRemoved);

        namesTable.clear();
        System.out.println(namesTable);
    }
}