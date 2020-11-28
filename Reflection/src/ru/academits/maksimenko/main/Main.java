package ru.academits.maksimenko.main;

import ru.academits.maksimenko.classes.Car;
import ru.academits.maksimenko.classes.House;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя класса: ");
        String name = scanner.nextLine();

        Class enteredClass = Class.forName(name);
        Constructor constructor = enteredClass.getConstructor(double.class, double.class, String.class);
        House house = (House) constructor.newInstance(15, 10, "White");

        System.out.println("Площадь дома: " + house.houseArea());
        System.out.println("Цвет дома: " + house.getColor());

        Car car = new Car("Toyota", 15000, 249, "White");

        Field field = car.getClass().getDeclaredField("color");

        field.setAccessible(true);

        String color = (String) field.get(car);

        System.out.println("Значение поля: " + color);

        field.set(car, color + "55");

        System.out.println("Значение поля: " + car.getColor());

        Field[] fieldsArray = car.getClass().getDeclaredFields();

        System.out.println("Массив полей: " + Arrays.toString(fieldsArray));
    }
}