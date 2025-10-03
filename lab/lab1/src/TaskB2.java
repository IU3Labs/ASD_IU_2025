import tools.Array;

import java.util.Scanner;

public class TaskB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter length for first array: ");
        int length1 = scanner.nextInt();
        int[] array1 = Array.getArray(length1, scanner);

        System.out.print("enter length for second array: ");
        int length2 = scanner.nextInt();
        int[] array2 = Array.getArray(length2, scanner);

        Array.printArray(array1);
        Array.printArray(array2);

        Array.printArray(integerToArray(arrayToInteger(array1) * arrayToInteger(array2)));
    }

    public static int arrayToInteger(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            int k = 1;
            for (int j = i + 1; j < array.length; j++) {
                k = k * 10;
            }
            result += array[i] * k;
        }
        return result;
    }

    public static int[] integerToArray(int integer) {
        int arrayLength = String.valueOf(integer).length();
        int[] array = new int[arrayLength];
        for (int i = arrayLength - 1; i >= 0; i--) {
            array[i] = integer % 10;
            integer = integer / 10;
        }
        return array;
    }
}
