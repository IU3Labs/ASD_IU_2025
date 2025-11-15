package lab1;

import java.util.Scanner;

public class FillArray {
    public static void fillArray(int[] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
    }
}