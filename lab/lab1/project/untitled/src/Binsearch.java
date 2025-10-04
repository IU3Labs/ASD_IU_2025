/*
Обязательное задание.
Релизовать алгоритм бинарного поиска двумя способами:
1) через цикл
2) рекурсивно
 */
import java.util.Scanner;

public class Binsearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = ArrayUtils.inputArray(sc);
        System.out.println("Ваш массив: ");
        ArrayUtils.bubbleSort(arr);
        ArrayUtils.printArray(arr);

        System.out.print("Введите элемент для поиска:");
        int el = sc.nextInt();

        int resultIter = searchIterative(arr, el);
        if (resultIter == -1) {
            System.out.println("Элемент не найден");
        } else {
            System.out.println("Элемент найден (итеративный метод),индекс: " + resultIter);
        }

        int resultRec = searchRecursive(arr, el, 0, arr.length - 1);
        if (resultRec == -1) {
            System.out.println("Элемент не найден");
        } else {
            System.out.println("Элемент найден (рекурсивный метод), индекс: " + resultRec);
        }
    }
    private static int searchIterative(int[] arr, int el) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == el) {
                return mid;
            } else if (arr[mid] < el) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    private static int searchRecursive(int[] arr, int el, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == el) {
            return mid;
        } else if (arr[mid] < el) {
            return searchRecursive(arr, el, mid + 1, right);
        } else {
            return searchRecursive(arr, el, left, mid - 1);
        }
    }
}



