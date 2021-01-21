package ru.academits.temperature_conversion.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Передано аргументов: " + args.length + ". Нужно передать 2 аргумента: путь к входному " +
                    "CSV файлу и путь к результирующему HTML файлу.");

            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])) {
            printWriter.print("<!doctype html>");
            printWriter.print("<html>");
            printWriter.print("<head><meta charset=\"utf-8\"><title>Таблица</title></head>");
            printWriter.print("<body>");
            printWriter.print("<table border=\"2\" width=\"600\" align=\"center\">");

            char cellSeparator = ',';
            char quote = '"';
            char less = '<';
            char more = '>';
            char amp = '&';

            int quotesCount = 0;

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                int lineLength = line.length();

                if (lineLength == 0) {
                    continue;
                }

                if (quotesCount % 2 == 0) {
                    printWriter.print("<tr><td>");
                }

                for (int i = 0; i < lineLength; i++) {
                    char currentCharacter = line.charAt(i);

                    if (currentCharacter == quote) {
                        ++quotesCount;

                        if (quotesCount % 3 == 0) {
                            printWriter.print(quote);
                        }

                        continue;
                    }

                    if (currentCharacter == less) {
                        printWriter.print("&lt;");
                        continue;
                    }

                    if (currentCharacter == more) {
                        printWriter.print("&gt;");
                        continue;
                    }

                    if (currentCharacter == amp) {
                        printWriter.print("&amp;");
                        continue;
                    }

                    if (currentCharacter == cellSeparator && quotesCount % 2 == 0) {
                        printWriter.print("</td><td>");

                        quotesCount = 0;

                        continue;
                    }

                    printWriter.print(currentCharacter);
                }

                if (quotesCount % 2 == 0) {
                    printWriter.print("</td></tr>");

                    quotesCount = 0;

                    continue;
                }

                printWriter.print("<br/>");
            }

            printWriter.print("</table></body></html>");
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}