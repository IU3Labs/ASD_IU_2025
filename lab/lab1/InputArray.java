package lab1;

import java.util.Scanner;

public class InputArray {
    private static Scanner input = new Scanner(System.in);

    public static int[] inputArray() {
        System.out.print("Введите длину массива: ");
        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < arrayLength; i++) {
            array[i] = input.nextInt();
        }
        return array;
    }
}