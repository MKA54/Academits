package ru.academits.maksimenko.list.list_main;

import ru.academits.maksimenko.list.list.ListItem;
import ru.academits.maksimenko.list.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> namesList = new SinglyLinkedList<>(new ListItem<>("Петр"));

        namesList.add("Андрей");
        namesList.add("Степан");
        namesList.add("Владимир");
        namesList.add("Константин");
        namesList.add(null);
        namesList.add("Василий");
        namesList.add("Григорий");

        System.out.println("Список: " + namesList);

        System.out.println("Размер списка: " + namesList.getSize());

        System.out.println("Первое значение списка: " + namesList.getFirsData());

        String name = namesList.getDataByIndex(6);

        System.out.println("Полученное значение по индексу: " + name);

        String oldValue = namesList.setDataByIndex(6, "Роман");
        System.out.println("Старое значение по индексу: " + oldValue);

        System.out.println("Список: " + namesList);

        oldValue = namesList.deleteItemByIndex(2);
        System.out.println("Удаленное значение по индексу: " + oldValue);

        namesList.addFirst("Виктор");
        System.out.println("Список: " + namesList);

        namesList.insertByIndex(7, "Глеб");
        System.out.println("Список: " + namesList);

        boolean isDeleted = namesList.deleteItemByValue("Владимир");
        System.out.println("Узел удален: " + isDeleted);

        oldValue = namesList.deleteFirstItem();
        System.out.println("Удаленное значение: " + oldValue);

        namesList.reverse();
        System.out.println("Список: " + namesList);

        SinglyLinkedList<String> copy = namesList.copy();
        System.out.println("Копия списка: " + copy);
    }
}