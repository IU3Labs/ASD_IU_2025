package lab3;

//Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
//таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
//данном массиве и вывести их. Дать комментарии. Вычислить сложность.

import java.util.Scanner;
import java.util.Arrays;

public class InversionCounter {

    private int inversionCount;
    public static void main(String[] args) {
        InversionCounter counter = new InversionCounter();
        Scanner scanner = new Scanner(System.in);
        int[] array = readArrayFromInput(scanner);
        scanner.close();
        System.out.println("Введенный массив: " + Arrays.toString(array));

        int totalInversions = counter.countInversions(array.clone());
        System.out.println("Общее количество инверсий: " + totalInversions);

        counter.findAndPrintAllInversions(array);
    }

    private static int[] readArrayFromInput(Scanner scanner) {
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();
        while (size <= 0) {
            System.out.print("Размер массива должен быть положительным. Введите снова: ");
            size = scanner.nextInt();
        }
        int[] array = new int[size];
        System.out.println("Введите " + size + " элементов массива:");

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public int countInversions(int[] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }

        inversionCount = 0;
        int[] temporaryArray = new int[array.length];
        countInversionsRecursive(array, temporaryArray, 0, array.length - 1);

        return inversionCount;
    }

    private void countInversionsRecursive(
            int[] array,
            int[] temporaryArray,
            int leftIndex,
            int rightIndex
    ) {
        if (leftIndex < rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            countInversionsRecursive(array, temporaryArray, leftIndex, middleIndex);
            countInversionsRecursive(array, temporaryArray, middleIndex + 1, rightIndex);
            mergeAndCount(array, temporaryArray, leftIndex, middleIndex, rightIndex);
        }
    }

    private void mergeAndCount(
            int[] array,
            int[] temporaryArray,
            int leftIndex,
            int middleIndex,
            int rightIndex
    ) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            temporaryArray[i] = array[i];
        }

        int leftPointer = leftIndex;
        int rightPointer = middleIndex + 1;
        int currentPosition = leftIndex;

        while (leftPointer <= middleIndex && rightPointer <= rightIndex) {
            if (temporaryArray[leftPointer] <= temporaryArray[rightPointer]) {
                array[currentPosition] = temporaryArray[leftPointer];
                leftPointer++;
            } else {
                array[currentPosition] = temporaryArray[rightPointer];
                rightPointer++;
                inversionCount += (middleIndex - leftPointer + 1);
            }
            currentPosition++;
        }

        while (leftPointer <= middleIndex) {
            array[currentPosition] = temporaryArray[leftPointer];
            leftPointer++;
            currentPosition++;
        }
    }

    public void findAndPrintAllInversions(int[] array) {
        if (array == null || array.length <= 1) {
            System.out.println("Массив пуст или содержит менее 2 элементов");
            return;
        }

        int inversionCount = 0;
        System.out.println("\nВсе найденные инверсии:");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    inversionCount++;
                    printInversion(i, j, array[i], array[j]);
                }
            }
        }

        if (inversionCount == 0) {
            System.out.println("Инверсий не найдено");
        }
    }

    private void printInversion(int firstIndex, int secondIndex, int firstValue, int secondValue) {
        System.out.printf(
                "  Индексы (%d, %d): %d > %d%n",
                firstIndex, secondIndex, firstValue, secondValue
        );
    }
}

