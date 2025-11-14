package Tasks;

import java.util.Scanner;


public class Main {

    public static void fillArray(int[] arr) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.print((i + 1) + "-й элемент массива: ");
            arr[i] = scan.nextInt();
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void arrayOutput(int[] arr) {
        System.out.print("Массив: ");
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + "]");
        System.out.print("\n");
    }

    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int recursiveBinarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return recursiveBinarySearch(arr, left, mid - 1, target);
        } else {
            return recursiveBinarySearch(arr, mid + 1, right, target);
        }
    }

    public static int[] arrayWork() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int size = scan.nextInt();
        int[] array = new int[size];
        return array;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] array = arrayWork();
        //добавил отдельный класс для работы с массивом

        fillArray(array);
        bubbleSort(array);
        arrayOutput(array);

        System.out.print("Введите число, которое хотите найти: ");
        int target = scan.nextInt();

        int index = binarySearchIterative(array, target);
        System.out.println("Элемент " + target + " имеет индекс " + index);

        int index2 = recursiveBinarySearch(array, 0, array.length - 1, target);
        System.out.println("Рекурсивным методом найден индекс " + index2);
    }
}

