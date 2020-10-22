package ru.academits.maksimenko.array_list.array_list_main;

import ru.academits.maksimenko.array_list.array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> names = new MyArrayList<>();
        System.out.println("Пустой список: " + names);

        List<String> namesList = new ArrayList<>(Arrays.asList("Роман", "Сергей", null));

        MyArrayList<String> collectionFromList = new MyArrayList<>(namesList);
        System.out.println("Список из коллекции: " + collectionFromList);

        names.add("Роман");
        names.add("Сергей");
        names.add("Владимир");
        names.add("Петр");
        names.add("Константин");
        names.add("Григорий");

        System.out.println("Список имен: " + names);

        int size = names.size();
        System.out.println("Размер списка: " + size);

        names.add(4, "Станислав");
        System.out.println("Список имен: " + names);

        boolean hasTest = names.isEmpty();
        System.out.println("Проверка на пустоту списка: " + hasTest);

        hasTest = names.contains("Василий");
        System.out.println("Проверка на наличие элемента в списке: " + hasTest);

        Object[] objects = names.toArray();
        System.out.println("Список объектов: " + Arrays.toString(objects));

        String[] namesArray = names.toArray(new String[0]);
        System.out.println("Список из массива строк: " + Arrays.toString(namesArray));

        hasTest = names.remove("Петр");
        System.out.println("Проверка на удаление объекта: " + hasTest);

        String name = names.get(4);
        System.out.println("Имя по индексу: " + name);

        int index = names.indexOf("Владимир");
        System.out.println("Индекс первого вхождения имени в списке: " + index);

        hasTest = names.containsAll(namesList);
        System.out.println("Проверка на наличие всех элементов коллекции в списке: " + hasTest);

        names.addAll(namesList);
        System.out.println("Список: " + names);

        names.addAll(0, namesList);
        System.out.println("Список: " + names);

        hasTest = names.removeAll(namesList);
        System.out.println("Наличие изменений в списке после удаления: " + hasTest);

        hasTest = names.retainAll(namesList);
        System.out.println("Наличие изменений в списке после удаления: " + hasTest);
    }
}