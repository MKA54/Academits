package ru.academits.csv.maksimenko;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Таблица.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] csv = line.split(",");
                System.out.println(Arrays.toString(csv));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}