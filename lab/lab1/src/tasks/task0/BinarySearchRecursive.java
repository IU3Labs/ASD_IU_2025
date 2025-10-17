package tasks.task0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearchRecursive {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void SorterAsc(double[] array) {
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



    public static boolean BinarySearchRecursion(double[] array, double number, int lowIdxOfElement, int highIdxOfElement){
        if (highIdxOfElement == lowIdxOfElement) {
            return array[lowIdxOfElement] == number;
        }

        int middleIdxOfElement = lowIdxOfElement + (highIdxOfElement - lowIdxOfElement) / 2;
        if (array[middleIdxOfElement] < number) {
            return BinarySearchRecursion(array, number, middleIdxOfElement + 1, highIdxOfElement);
        } else if (array[middleIdxOfElement] > number) {
            return BinarySearchRecursion(array, number, lowIdxOfElement, middleIdxOfElement - 1);
        }
        return true;
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

        SorterAsc(array);
        if (BinarySearchRecursion(array, numberToFind, 0, array.length - 1)){
            System.out.println("Хвала богам, мы нашли его!");
        } else System.out.println("Увы, не получилось.");
    }


}
