package lab1;

import java.util.Scanner;

// Реализовать алгоритм бинарного поиска двумя способами.

public class RequiredTask {
    public static void main(String[] args) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90, 65, 3, 21};
        System.out.println("Начальный массив: ");
        printStartArray(arr);

        insertionSort(arr);

        System.out.println("Отсортированный массив: ");
        printFinalArray(arr);

        System.out.println("Введите элемент: ");

        int number = inputValue();
        binarySearch(arr, number);
        recursiveBinarySearch(arr, 0, arr.length - 1, number);
    }

    public static void printStartArray(int[] arr) {      //Вывод начального массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] arr) {            //Сортировка вставками
        for (int left = 0; left < arr.length; left++) {
            int value = arr[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = value;
        }
    }

    public static void printFinalArray(int[] arr) {       //Вывод отсортированного массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int inputValue() {  //Ввод числа с клавиатуры
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        return number;
    }

    public static void binarySearch(int[] arr, int number) {    //Бинарный поиск
        int left = 0;
        int right = arr.length - 1;
        boolean flag = false;

        while (left <= right) {
            int mid_number = (left + right) / 2;

            if (arr[mid_number] == number) {
                System.out.println("Элемент " + number + " найден");
                flag = true;
                break;
            } else if (arr[mid_number] < number) {
                left = mid_number + 1;
            } else {
                right = mid_number - 1;
            }
        }
        if (flag != true) {
            System.out.println("Элемент " + number + " не найден");
        }
    }

    public static int recursiveBinarySearch(int arr[], int left, int right, int number) {
        if (left > right) {
            System.out.println("Элемент " + number + " не найден");
            return -1;
        }

        int mid_number = (left + right) / 2;
        if (arr[mid_number] == number) {
            System.out.println("Элемент " + number + " найден");
            return mid_number;
        } else if (arr[mid_number] < number) {
            return recursiveBinarySearch(arr, mid_number + 1, right, number);
        } else {
            return recursiveBinarySearch(arr, left, mid_number - 1, number);
        }
    }
}

