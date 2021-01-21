package ru.academits.temperature_conversion.maksimenko.list_main;

import ru.academits.temperature_conversion.maksimenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> namesList = new SinglyLinkedList<>("Петр");

        namesList.add("Андрей");
        namesList.add("Степан");
        namesList.add("Владимир");
        namesList.add("Константин");
        namesList.add(null);
        namesList.add("Василий");
        namesList.add("Григорий");

        System.out.println("Список: " + namesList);

        System.out.println("Размер списка: " + namesList.getSize());

        System.out.println("Первое значение списка: " + namesList.getFirstData());

        String name = namesList.getDataByIndex(6);

        System.out.println("Полученное значение по индексу: " + name);

        String oldData = namesList.setDataByIndex(6, "Роман");
        System.out.println("Старое значение по индексу: " + oldData);

        System.out.println("Список: " + namesList);

        oldData = namesList.deleteByIndex(2);
        System.out.println("Удаленное значение по индексу: " + oldData);

        namesList.addFirst("Виктор");
        System.out.println("Список: " + namesList);

        namesList.insertByIndex(7, "Глеб");
        System.out.println("Список: " + namesList);

        boolean isDeleted = namesList.deleteByData("Владимир");
        System.out.println("Узел удален: " + isDeleted);

        oldData = namesList.deleteFirst();
        System.out.println("Удаленное значение: " + oldData);

        namesList.reverse();
        System.out.println("Список: " + namesList);

        SinglyLinkedList<String> copy = namesList.copy();
        System.out.println("Копия списка: " + copy);
    }
}