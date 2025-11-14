import java.util.Scanner;

public class ArrayTools {
    private static final Scanner scanner = new Scanner(System.in);


    public static int[] createArray() {
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Некорректный размер массива");
            return createArray(); // рекурсивный вызов для корректного ввода
        }

        int[] array = new int[size];
        System.out.println("Введите " + size + " целых чисел:");

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент [" + i + "]: ");
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static int[] createArray(int size) {

        int[] array = new int[size];
        System.out.println("Введите " + size + " целых чисел:");

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент [" + i + "]: ");
            array[i] = scanner.nextInt();
        }

        return array;
    }
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}