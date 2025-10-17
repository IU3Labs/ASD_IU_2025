package tasks.task0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import helper.ArrayTools;

public class BinarySearchIterative {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
        double[] array = ArrayTools.createDoubleArray();
        ArrayTools.sorterAscArray(array);
        System.out.print("Какое число ищем, мой господин? -> ");
        double numberToFind = Double.parseDouble(reader.readLine());
        if (binarySearchIterative(array, numberToFind)) {
            System.out.println("Хвала богам, мы нашли его!");
        } else {
            System.out.println("Увы, не получилось.");
        }
    }



}
