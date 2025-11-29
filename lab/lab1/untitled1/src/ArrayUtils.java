import java.util.Scanner;
public class ArrayUtils {
    public static int[] inputArray(Scanner scanner) {
        System.out.print("Введите длину массива: ");
        int length = scanner.nextInt();
        int[] array = new int[length];
        System.out.println("Введите элементы массива в порядке возрастания: ");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i+1 < array.length) {
                System.out.print(array[i] + " ");
            }
            else {
                System.out.println(array[i]);
            }
        }
    }
}
