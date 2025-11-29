// ArrayUtility.java

import java.util.Scanner;

public class ArrayUtility {

    public static int[] fillArray(Scanner scanner, int size, String prompt) {
        if (prompt != null && !prompt.isEmpty()) {
            System.out.println(prompt);
        }
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}