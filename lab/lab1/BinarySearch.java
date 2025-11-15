import java.util.Scanner;

public class BinarySearch {

    private static final Scanner SCANNER = new Scanner(System.in);


    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!SCANNER.hasNextInt()) {
            System.out.println("Ошибка: Введите целое число.");
            System.out.print(prompt);
            SCANNER.next();
        }
        return SCANNER.nextInt();
    }


    private static int[] readIntArray() {
        int n = readInt("Введите количество элементов в массиве: ");
        if (n < 0) {
            System.out.println("Размер не может быть отрицательным. Установлен размер 0.");
            n = 0;
        }

        int[] arr = new int[n];
        if (n > 0) {
            System.out.println("Введите " + n + " элементов массива:");
            for (int i = 0; i < n; i++) {
                arr[i] = readInt("Элемент [" + i + "]: ");
            }
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static void sortArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Обмен arr[j] и arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static int binarySearchIterative(int[] sortedArray, int key) {
        int low = 0;
        int high = sortedArray.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedArray[mid] == key) {
                return mid;
            } else if (sortedArray[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int recursiveSearch(int[] arr, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] < key) {
            return recursiveSearch(arr, key, mid + 1, high);
        } else {
            return recursiveSearch(arr, key, low, mid - 1);
        }
    }

    public static int binarySearchRecursive(int[] sortedArray, int key) {
        return recursiveSearch(sortedArray, key, 0, sortedArray.length - 1);
    }


    public static void main(String[] args) {

        System.out.println("--- Обязательное Задание: Бинарный Поиск с вводом с клавиатуры ---");

        int[] arr = readIntArray();

        System.out.println("Введенный массив:");
        printArray(arr);

        sortArray(arr);

        System.out.println("Отсортированный массив (для бинарного поиска):");
        printArray(arr);

        int keyToSearch = readInt("Введите элемент для поиска: ");

        int indexIterative = binarySearchIterative(arr, keyToSearch);

        System.out.println("\n--- Итеративный Поиск ---");
        if (indexIterative != -1) {
            System.out.println("Элемент " + keyToSearch + " найден на позиции " + indexIterative);
        } else {
            System.out.println("Элемент " + keyToSearch + " не найден.");
        }

        int indexRecursive = binarySearchRecursive(arr, keyToSearch);

        System.out.println("\n--- Рекурсивный Поиск ---");
        if (indexRecursive != -1) {
            System.out.println("Элемент " + keyToSearch + " найден на позиции " + indexRecursive);
        } else {
            System.out.println("Элемент " + keyToSearch + " не найден.");
        }
    }
}