/*
Дан массив целых чисел. Минимальное количество элементов – 5.
Вернуть число, которое является суммой двух наименьших положительных чисел.
*/
import java.util.Scanner;
public class ArraySummary {
    private static int[] inputArray(Scanner scanner) {
        System.out.print("Enter array size (>=5): ");
        int n = scanner.nextInt();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter number (" + (i + 1) + "/" + n + "): ");
            array[i] = scanner.nextInt();
        }
        return array;
    }
    private static int summaryOfTwoSmallest(int[] arr) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (arr[i] < min1) {
                    min2 = min1;
                    min1 = arr[i];
                } else if (arr[i] < min2) {
                    min2 = arr[i];
                }
            }
        }
        if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
            return -1;
        }
        return min1 + min2;
    }
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = inputArray(scanner);

        System.out.println("Array: ");
        printArray(arr);

        int result = summaryOfTwoSmallest(arr);
        if (result == -1) {
            System.out.println("Less then 2 positiv nums(");
        } else {
            System.out.println("Summ of 2: " + result);
        }
    }


}
