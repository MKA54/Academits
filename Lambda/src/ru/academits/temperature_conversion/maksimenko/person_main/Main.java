package ru.academits.temperature_conversion.maksimenko.person_main;

import ru.academits.temperature_conversion.maksimenko.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person(15, "Глеб"),
                new Person(12, "Василий"),
                new Person(21, "Константин"),
                new Person(24, "Владимир"),
                new Person(29, "Илья"),
                new Person(31, "Максим"),
                new Person(35, "Николай"),
                new Person(38, "Генадий"),
                new Person(41, "Василий"),
                new Person(47, "Петр")
        ));

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Список уникальных имен: " + uniqueNamesList);

        String uniqueNamesString = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> minorsList = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        double minorsAverageAge = minorsList.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("Средний возраст несовершеннолетних: " + minorsAverageAge);

        Map<String, Double> namesakesAverageAges = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        namesakesAverageAges.forEach((name, age) -> System.out.printf("%s, средний возраст: %.2f%n", name, age));

        List<Person> middleAgedPeopleNames = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 40)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        middleAgedPeopleNames.forEach(System.out::println);
    }
}