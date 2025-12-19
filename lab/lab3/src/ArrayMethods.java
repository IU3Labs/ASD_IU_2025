import java.util.Scanner;

public class ArrayMethods {
    public static int[] readData(Scanner inputReader) {
        System.out.print("Введите размер массива: ");
        int size = inputReader.nextInt();
        int[] data = new int[size];
        System.out.println("Введите элементы:");
        for (int i = 0; i < size; i++) {
            data[i] = inputReader.nextInt();
        }
        return data;
    }

    public static void displayData(int[] data) {
        for (int value : data) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}