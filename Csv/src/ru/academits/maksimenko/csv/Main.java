package ru.academits.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])) {
            String line;

            printWriter.print("<!doctype html>");
            printWriter.print("<html>");
            printWriter.print("<head><meta charset=\"utf-8\"><title>Таблица</title></head>");
            printWriter.print("<body>");
            printWriter.print("<table width=\"600\" align=\"center\">");

            char split = ',';
            char quote = '"';
            char less = '<';
            char more = '>';
            char amp = '&';

            int lineNumber = 1;
            int quotationCount = 0;

            StringBuilder csv = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                int lineLength = line.length();

                if (lineLength == 0) {
                    continue;
                }

                if (quotationCount % 2 == 0) {
                    printWriter.print("<tr>");
                }

                String orientation = lineNumber == 1 ? "center" : "left";

                for (int i = 0, j = 1; i < lineLength; i++, j++) {
                    char current = line.charAt(i);
                    char next = 0;

                    if (j != lineLength) {
                        next = line.charAt(j);
                    }

                    if (current == quote && next != quote) {
                        ++quotationCount;

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

                    if (current == split) {
                        printWriter.print("<td align=\"" + orientation + "\">" + csv + "</td>");

                        csv.delete(0, csv.length());

                        continue;
                    }

                    csv.append(current);
                }

                if (quotationCount % 2 == 0) {
                    printWriter.print("<td align=\"" + orientation + "\">" + csv + " </td></tr>");

                    quotationCount = 0;
                    csv.delete(0, csv.length());

                    lineNumber++;

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