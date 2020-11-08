package ru.academits.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int index1 = args[0].lastIndexOf(".csv");
        int index2 = args[1].lastIndexOf(".html");

        if (args.length != 2 || index1 != args[0].length() - 4 || index2 != args[1].length() - 5) {
            System.out.println("Аргументы программы заданы неправильно. Путь для передачи аргументов: Run -> Edit Configuration -> Application " +
                    "в поле Program Arguments указываете имя файла с данными в формате csv и через пробел имя файла в фромате html " +
                    "в который будут сохранены данные.");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])) {
            printWriter.print("<!doctype html>");
            printWriter.print("<html>");
            printWriter.print("<head><meta charset=\"utf-8\"><title>Таблица</title></head>");
            printWriter.print("<body>");
            printWriter.print("<table border=\"2\" width=\"600\" align=\"center\">");

            char split = ',';
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
                    char current = line.charAt(i);

                    if (current == quote) {
                        ++quotesCount;

                        if (quotesCount % 3 == 0) {
                            printWriter.print(quote);
                        }

                        continue;
                    }

                    if (current == less) {
                        printWriter.print("&lt;");
                        continue;
                    }

                    if (current == more) {
                        printWriter.print("&gt;");
                        continue;
                    }

                    if (current == amp) {
                        printWriter.print("&amp;");
                        continue;
                    }

                    if (current == split && quotesCount % 2 == 0) {
                        printWriter.print("</td><td>");

                        quotesCount = 0;

                        continue;
                    }

                    printWriter.print(current);
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