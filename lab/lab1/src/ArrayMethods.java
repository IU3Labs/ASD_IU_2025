import java.util.Scanner;

public class ArrayMethods {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] newArray() {
        System.out.println("Введите число элементов массива:");
        int length = scanner.nextInt();
        int[] array = new int[length];
        System.out.println("Введите " + length + " элементов:");

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }
    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }
}


