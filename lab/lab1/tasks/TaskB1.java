package tasks;

import java.util.Scanner;

public class TaskB1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = getArray(scanner);
        System.out.print("Введенный массив: ");
        ArrayFunc.printArray(array);
        int result = findFrequency(array);
        printResult(result);
    }

    private static int[] getArray(Scanner scanner) {
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите " + size + " целых чисел:");

        for(int i = 0; i < size; ++i) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    private static int findFrequency(int[] array) {
        int maxNumber = -1;

        for(int i = 0; i < array.length; ++i) {
            int currentNumber = array[i];
            int frequency = countFrequency(array, currentNumber);
            if (frequency == currentNumber && currentNumber > maxNumber) {
                maxNumber = currentNumber;
            }
        }

        return maxNumber;
    }

    private static int countFrequency(int[] array, int target) {
        int count = 0;

        for(int i = 0; i < array.length; ++i) {
            if (array[i] == target) {
                ++count;
            }
        }

        return count;
    }

    private static void printResult(int result) {
        if (result != -1) {
            System.out.println("Найдено число: " + result);
        } else {
            System.out.println(" -1 ");
        }

    }
}
