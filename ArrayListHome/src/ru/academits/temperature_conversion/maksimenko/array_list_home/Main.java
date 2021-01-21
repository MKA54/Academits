package ru.academits.temperature_conversion.maksimenko.array_list_home;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileList = getStringsListFromFile("Список.txt");
        System.out.println("Список из файла: " + fileList);

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(9, 9, 1, 2, 2, 5, 3, 1, 4, 5, 9, 6, 7, 3, 8, 9));

        deleteEvenNumbers(numbersList);
        System.out.println("Список целых чисел после удаления четных чисел: " + numbersList);

        ArrayList<Integer> uniqueNumbersList = getUniqueNumbers(numbersList);
        System.out.println("Список уникальных значений: " + uniqueNumbersList);
    }

    public static ArrayList<String> getStringsListFromFile(String filePath) {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        return list;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbersList) {
        for (int i = 0; i < numbersList.size(); i++) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);

                i--;
            }
        }
    }

    public static ArrayList<Integer> getUniqueNumbers(ArrayList<Integer> numbersList) {
        ArrayList<Integer> uniqueNumbersList = new ArrayList<>(numbersList.size());

        for (Integer value : numbersList) {
            if (!uniqueNumbersList.contains(value)) {
                uniqueNumbersList.add(value);
            }
        }

        return uniqueNumbersList;
    }
}