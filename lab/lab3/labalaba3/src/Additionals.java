import java.util.Scanner;

public class Additionals {

    private static final Scanner scanner = new Scanner(System.in);

    public static int[] inputArray() {
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int inputK() {
        System.out.print("Введите k: ");
        return scanner.nextInt();
    }
}