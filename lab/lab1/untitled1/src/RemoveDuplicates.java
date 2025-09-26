import java.util.Scanner;

public class RemoveDuplicates {

    public static int[] removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        int[] result = new int[arr.length];
        int index = 0;
        result[index++] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                result[index++] = arr[i];
            }
        }

        int[] finalResult = new int[index];
        for (int i = 0; i < index; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Введите отсортированный массив: ");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] uniqueArr = removeDuplicates(arr);

        System.out.print("Массив без дубликатов: ");
        for (int num : uniqueArr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
