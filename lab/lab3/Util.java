import java.util.Scanner;

public class Util {

    private static final Scanner scanner = new Scanner(System.in);

    // Ввод размера массива
    public static int inputSize() {
        System.out.print("Введите количество элементов массива: ");
        return scanner.nextInt();
    }

    // Ввод элементов массива
    public static int[] inputArray(int n) {
        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    // Печать массива
    public static void printArray(int[] arr) {
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
