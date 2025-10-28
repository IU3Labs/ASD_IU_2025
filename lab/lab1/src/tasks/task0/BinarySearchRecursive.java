//Алгоритм бинарного поиска, реализованный рекурсивным методом
package tasks.task0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import helper.ArrayTools;


public class BinarySearchRecursive {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static boolean binarySearchRecursion(double[] array, double number, int lowIdxOfElement, int highIdxOfElement) {
        if (highIdxOfElement == lowIdxOfElement) {
            return array[lowIdxOfElement] == number;
        }

        int middleIdxOfElement = lowIdxOfElement + (highIdxOfElement - lowIdxOfElement) / 2;
        if (array[middleIdxOfElement] < number) {
            return binarySearchRecursion(array, number, middleIdxOfElement + 1, highIdxOfElement);
        } else if (array[middleIdxOfElement] > number) {
            return binarySearchRecursion(array, number, lowIdxOfElement, middleIdxOfElement - 1);
        }
        return true;
    }

    public static void binarySearch() throws IOException {
        double[] array = ArrayTools.createDoubleArray();
        System.out.print("Какое число ищем, мой господин? -> ");
        double numberToFind = Double.parseDouble(reader.readLine());
        if (binarySearchRecursion(array, numberToFind, 0, array.length - 1)) {
            System.out.println("Хвала богам, мы нашли его!");
        } else {
            System.out.println("Увы, не получилось.");
        }
    }
}
