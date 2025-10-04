/*
Дан массив целых чисел. Минимальное количество элементов – 5.
Вернуть число, которое является суммой двух наименьших положительных чисел.
*/
import java.util.Scanner;
public class ArraySummary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = ArrayUtil.inputArray(scanner);

        System.out.println("Массив:");
        ArrayUtil.printArray(arr);

        int result = summaryOfTwoSmallest(arr);
        if (result == -1) {
            System.out.println("В массиве меньше двух положительных чисел.");
        } else {
            System.out.println("Сумма двух наименьших положительных чисел: " + result);
        }
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
}