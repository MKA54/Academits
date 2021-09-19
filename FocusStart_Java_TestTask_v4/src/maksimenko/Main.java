package maksimenko;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        if (!Objects.equals(args[0], "-i") || !Objects.equals(args[1], "out.txt")) {
            System.out.println("Аргументы переданы не в правильном порядке.");
            System.out.println("Порядок передачи аргументо: -i или -s out.txt in.txt.");
        }

        if (args.length <= 2) {
            System.out.println("Передано аргументов: " + args.length + ". Нужно передать минимум 1 файл для сортировки");
            return;
        }

        int argumentsCount = args.length;
        int startingIndexFiles = 2;
        String recordedFileName = args[1];

        if (Objects.equals(args[0], "-i")) {
            int[][] argumentsArray = new int[argumentsCount - startingIndexFiles][];

            for (int i = startingIndexFiles, j = 0; i < argumentsCount; i++, j++) {
                int[] array;

                String fileName = "maksimenko\\" + args[i];

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                    array = bufferedReader.lines().
                            mapToInt(Integer::parseInt).
                            toArray();
                } catch (IOException | NumberFormatException e) {
                    continue;
                }

                argumentsArray[j] = array;
            }

            int elementsCount = Arrays.stream(argumentsArray).
                    mapToInt(e -> e.length).
                    sum();

            int[] mergeArray = new int[elementsCount];

            int copyIndexArray = 0;

            for (int[] ints : argumentsArray) {
                System.arraycopy(ints, 0, mergeArray, copyIndexArray, ints.length);
                copyIndexArray += ints.length;
            }

            mergeSort(mergeArray, 0, mergeArray.length - 1);

            try (PrintWriter printWriter = new PrintWriter(recordedFileName)) {
                Arrays.stream(mergeArray).
                        forEach(printWriter::println);
            } catch (Exception ignored) {
            }
        }

        if (Objects.equals(args[0], "-s")) {
            List<Character> chars = new ArrayList<>();

            for (int i = startingIndexFiles, j = 0; i < argumentsCount; i++, j++) {
                String fileName = "maksimenko\\" + args[i];
                String symbol;

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                    while ((symbol = bufferedReader.readLine()) != null) {
                        chars.add(symbol.charAt(0));
                    }

                } catch (IOException | NumberFormatException ignored) {
                }
            }

            int[] characterUnicodeArray = new int[chars.size()];

            for (int i = 0; i < characterUnicodeArray.length; i++) {
                characterUnicodeArray[i] = (int) chars.get(i);
            }

            mergeSort(characterUnicodeArray, 0, characterUnicodeArray.length - 1);

            char[] mappedCharactersArray = new char[characterUnicodeArray.length];

            for (int i = 0; i < mappedCharactersArray.length; i++) {
                mappedCharactersArray[i] = (char) characterUnicodeArray[i];
            }

            try (PrintWriter printWriter = new PrintWriter(recordedFileName)) {
                for (char c : mappedCharactersArray) {
                    printWriter.println(c);
                }
            } catch (Exception ignored) {
            }
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        System.arraycopy(array, left, leftArray, 0, lengthLeft);
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}