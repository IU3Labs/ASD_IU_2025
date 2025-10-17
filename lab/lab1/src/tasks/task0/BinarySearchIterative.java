package tasks.task0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearchIterative {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void sorterAsc(double[] array) {
        double arrayLength = array.length;
        for (int i = 1; i < arrayLength; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arrayLength - i; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    public static boolean binarySearchIterative(double[] array, double number) {
        int highIdxOfElement = array.length - 1;
        int lowIdxOfElement = 0;
        while (lowIdxOfElement <= highIdxOfElement) {
            int middleIdxOfElement = lowIdxOfElement + (highIdxOfElement - lowIdxOfElement) / 2;
            if (array[middleIdxOfElement] < number) {
                lowIdxOfElement = middleIdxOfElement + 1;
            } else if (array[middleIdxOfElement] > number) {
                highIdxOfElement = middleIdxOfElement - 1;
            } else if (array[middleIdxOfElement] == number) {
                return true;
            }
        }
        return false;
    }

    public static void binarySearch() throws IOException {
        System.out.print("Добро пожаловать, мой господин!\nВведите размер массива: ");

        int sizeOfArray = Integer.parseInt(reader.readLine());
        double[] array = new double[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            System.out.print("Введите " + (i + 1) + " элемент массива: ");
            array[i] = Double.parseDouble(reader.readLine());
        }
        System.out.print("Какое число ищем, мой господин? -> ");
        double numberToFind = Double.parseDouble(reader.readLine());

        sorterAsc(array);
        if (binarySearchIterative(array, numberToFind)){
            System.out.println("Хвала богам, мы нашли его!");
        } else System.out.println("Увы, не получилось.");

    }


}
