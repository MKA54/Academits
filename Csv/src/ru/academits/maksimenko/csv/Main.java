package ru.academits.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
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

            int quotationCount = 0;

            StringBuilder csv = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                int lineLength = line.length();

                if (lineLength == 0) {
                    csv.append("<p></p>");

                    continue;
                }

                if (quotationCount % 2 == 0) {
                    printWriter.print("<tr>");
                }

                for (int i = 0; i < lineLength; i++) {
                    char current = line.charAt(i);

                    if (current == quote) {
                        ++quotationCount;

                        if (quotationCount % 3 == 0) {
                            csv.append(quote);
                        }

                        continue;
                    }

                    if (current == less) {
                        csv.append("&lt;");
                        continue;
                    }

                    if (current == more) {
                        csv.append("&gt;");
                        continue;
                    }

                    if (current == amp) {
                        csv.append("&amp;");
                        continue;
                    }

                    if (current == split && quotationCount % 2 == 0) {
                        printWriter.print("<td>" + csv + "</td>");

                        csv.delete(0, csv.length());

                        quotationCount = 0;

                        continue;
                    }

                    csv.append(current);
                }

                if (quotationCount % 2 == 0) {
                    printWriter.print("<td>" + csv + " </td></tr>");

                    quotationCount = 0;
                    csv.delete(0, csv.length());

                    continue;
                }

                csv.append("<br/>");
            }

            printWriter.print("</table></body></html>");
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}