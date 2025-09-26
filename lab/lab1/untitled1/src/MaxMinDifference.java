import java.util.Scanner;

public class MaxMinDifference {

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите массив:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int findMaxMinDifference(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        return max - min;
    }

    public static void main(String[] args) {
        int[] array = inputArray();

        int result = findMaxMinDifference(array);

        System.out.println("Разница между максимальным и минимальным элементом: " + result);
    }
}