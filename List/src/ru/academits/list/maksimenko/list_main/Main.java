package ru.academits.list.maksimenko.list_main;

import ru.academits.list.maksimenko.list.ListItem;
import ru.academits.list.maksimenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> namesList = new SinglyLinkedList<>(new ListItem<>("Петр"));
        System.out.println(namesList);

        ListItem<String> name2 = new ListItem<>("Андрей");
        ListItem<String> name3 = new ListItem<>("Степан");
        ListItem<String> name4 = new ListItem<>("Владимир");
        ListItem<String> name5 = new ListItem<>("Константин");
        ListItem<String> name6 = new ListItem<>("Сергей");
        ListItem<String> name7 = new ListItem<>("Василий");
        ListItem<String> name8 = new ListItem<>("Григорий");

        System.out.println("Размер списка: " + namesList.getSize());
    }
}