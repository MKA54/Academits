package ru.academits.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Таблица.csv"));
             PrintWriter printWriter = new PrintWriter("Таблица.html")) {
            String line;

            printWriter.print("<table>");

            while ((line = bufferedReader.readLine()) != null) {
                String[] csv = line.replaceAll("<", "&lt").replaceAll(">", "&gt").
                        replaceAll("&", "&amp").split(",");

                printWriter.print("<tr>");

                for (String s : csv) {
                    printWriter.printf("<td>%s</td> ", s);
                }

                printWriter.print("</tr><br/>");
            }

            printWriter.print("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}