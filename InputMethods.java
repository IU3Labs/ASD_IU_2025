import java.util.Scanner;

public class InputMethods {

    // Метод ввода массива
    public static int[] inputArray() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        return arr;
    }

    public static int inputK() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите k: ");
        return sc.nextInt();
    }

    public static void main(String[] args) {
        int[] myArray = inputArray();

        System.out.println("Ваш массив:");
        for (int x : myArray) {
            System.out.print(x + " ");
        }
    }
}
