package binsearch;

import java.util.Scanner;

public class IterationSearch {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] arr = Utils.readArray(scanner);
        arr = Utils.quickSort(arr, 0, arr.length - 1);
        int target = takeTarget(scanner);
        int target_index = iterationSearch(arr, target);
        showResults(target, target_index);
    }

    private static int iterationSearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int takeTarget(Scanner scanner){
        int target;
        System.out.println("Введите искомую величину:");
        target = scanner.nextInt();
        return target;
    }

    private static void showResults(int target, int target_index){
        if (target_index == -1){
            System.out.println("Число " + target + " не найдено:(");
        }
        else {
            System.out.println("Число " + target + " найдено!");
        }
    }
}