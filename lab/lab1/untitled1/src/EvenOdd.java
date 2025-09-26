import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.println("Введите массив:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        sortEvenOdd(arr);

        System.out.println("Отсортированный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void sortEvenOdd(int[] arr) {
        int[] evenArr = new int[arr.length];
        int[] oddArr = new int[arr.length];

        int evenIndex = 0, oddIndex = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                evenArr[evenIndex++] = num;
            } else {
                oddArr[oddIndex++] = num;
            }
        }

        int index = 0;

        for (int i = 0; i < evenIndex; i++) {
            arr[index++] = evenArr[i];
        }

        for (int i = 0; i < oddIndex; i++) {
            arr[index++] = oddArr[i];
        }
    }
}
