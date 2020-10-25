package ru.academits.maksimenko.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter printWriter = new PrintWriter(args[1])) {
            String line;

            printWriter.print("<table>");

            int m = 0;

            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder csv = new StringBuilder();

                printWriter.print("<tr>");

                char split = ',';
                char quote = '"';
                char less = '<';
                char more = '>';
                char amp = '&';

                int lineLength = line.length();

                for (int i = m, j = 1; i < lineLength; i++, j++) {
                    if ( i == 0){
                        csv.append("<td>");
                    }

                    char current = line.charAt(i);
                    char next = 0;

                    if (j != lineLength) {
                        next = line.charAt(j);
                    }

                    if (current == quote && next == split) {
                        current = next;

                        i += 2;
                        j += 2;
                    }

                    if (current == quote && next == quote) {
                        continue;
                    }


                    if (current == less && next != quote) {
                        csv.append("&lt;");
                        continue;
                    }

                    if (current == more && next != quote) {
                        csv.append("&gt;");
                        continue;
                    }

                    if (current == amp && next != quote) {
                        csv.append("&amp;");
                        continue;
                    }

                    if (current == split && next != quote) {
                        csv.append("</td>");
                        m = i;

                        continue;
                    }

                    csv.append(current);
                }

                if (m == lineLength) {
                    printWriter.printf("%s </tr><br/>", csv);

                    m = 0;
                }

            }

            printWriter.print("</table>");
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}