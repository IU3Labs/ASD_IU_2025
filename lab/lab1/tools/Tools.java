package tools;

import java.util.Scanner;

public class Tools {
    public static int[] getArray(Scanner scanner) {
        System.out.print("Input list length: ");
        int num = scanner.nextInt();
        int[] array = new int[num];
        System.out.print("Input list: ");
        for (int i = 0; i < num; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }
}
