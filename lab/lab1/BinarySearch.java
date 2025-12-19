import java.util.Scanner;
//Реализовать алгоритм бинарного поиска двумя способами.
public class BinarySearch {

    public static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (array[middle] == target) {
                return middle;
            } else if (array[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    private static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = (left + right) / 2;

        if (array[middle] == target) {
            return middle;
        } else if (array[middle] > target) {
            return binarySearchRecursive(array, target, left, middle - 1);
        } else {
            return binarySearchRecursive(array, target, middle + 1, right);
        }
    }

    private static void printResult(int index, int[] array, String methodName) {
        if (index != -1) {
            System.out.println(methodName + " — Нашли: индекс = " + index + ", значение = " + array[index]);
        } else {
            System.out.println(methodName + " — Не нашли :(");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.print("Введите элементы массива по возрастанию: ");
        for (int i=0; i<n; i++){
            array[i] = scanner.nextInt();
        }

        System.out.println("Введите положительное число: ");
        int target = scanner.nextInt();


        int idxIter = binarySearchIterative(array, target);
        printResult(idxIter, array, "Итеративный поиск");

        int idxRec = binarySearchRecursive(array, target, 0, array.length - 1);
        printResult(idxRec, array, "Рекурсивный поиск");
    }
}