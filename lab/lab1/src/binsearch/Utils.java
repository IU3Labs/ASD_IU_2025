package binsearch;

import java.util.Scanner;

public class Utils {

    public static int[] quickSort(int[] arr, int low, int high){
        if (low < high) {
            int pivot = partition(arr, low, high);

            arr = quickSort(arr, low, pivot);
            arr = quickSort(arr, pivot + 1, high);

        }
        return arr;
    }

    private static int partition(int[] arr, int low, int high){
        int i = low;
        int j = high;
        int mid_index = (high - low)/2 + low;
        int pivot = arr[mid_index];

        while (true){
            while (arr[i] < pivot){
                i++;
            }
            while (arr[j] > pivot){
                j--;
            }
            if (i >= j){
                return j;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] readArray(Scanner scanner){
        System.out.println("Введите число элементов массива:");
        int num = scanner.nextInt();
        int[] arr = new int[num];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i <= num-1; i++){
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

}
