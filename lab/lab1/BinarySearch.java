import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearch {


    private static final int NOT_FOUND_INDEX = -1;

    // Метод для ввода размера массива с проверкой корректности.
    public static int inputArraySize(BufferedReader reader) throws IOException {
        int size = 0;
        while (true) {
            System.out.print("Введите размер массива: ");
            try {
                // Пытаемся считать и преобразовать строку в число
                size = Integer.parseInt(reader.readLine());
                if (size > 0) {
                    break;
                } else {
                    System.out.println("Размер массива должен быть положительным числом!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
        return size;
    }

    //Метод для заполнения массива значениями от пользователя.
    public static int[] fillArray(BufferedReader reader, int size) throws IOException {
        int[] array = new int[size];
        System.out.println("Введите элементы массива:");

        // Приветствие было изменено, чтобы не требовать от пользователя ручной сортировки.
        for (int i = 0; i < size; i++) {
            while (true) {
                System.out.print("Элемент [" + i + "]: ");
                try {
                    array[i] = Integer.parseInt(reader.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введите целое число.");
                }
            }
        }
        return array;
    }

    //Метод для сортировки массива с помощью пузырьковой сортировки (Bubble Sort).
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Обмен элементов
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // если внутренний цикл не сделал обменов, массив отсортирован
            if (!swapped) {
                break;
            }
        }
    }

    // Метод для вывода массива в консоль.

    public static void printArray(int[] array, String title) {
        System.out.print(title + ": [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    //Метод для ввода целевого значения для поиска.

    public static int inputTargetValue(BufferedReader reader) throws IOException {
        int target = 0;
        while (true) {
            System.out.print("Введите целевое значение для поиска: ");
            try {
                target = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
        return target;
    }

    // Рекурсивная реализация бинарного поиска.

    public static int recursiveBinarySearch(int[] arr, int target, int left, int right) {
        // Базовый случай: границы пересеклись, элемент не найден.
        if (left > right) {
            return NOT_FOUND_INDEX;
        }

        // Вычисление середины, предотвращающее переполнение
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            // Ищем в правой половине
            return recursiveBinarySearch(arr, target, mid + 1, right);
        } else {
            // Ищем в левой половине
            return recursiveBinarySearch(arr, target, left, mid - 1);
        }
    }
    public static int iterativeBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Вычисление середины, предотвращающее переполнение
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid; // значение найдено
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return NOT_FOUND_INDEX; //  не найдено
    }


    public static void printSearchResults(int target, int recursiveIndex, int iterativeIndex) {
        System.out.println("\nРезультаты поиска для значения " + target + ":");

        if (recursiveIndex != NOT_FOUND_INDEX) {
            System.out.println("Рекурсивный поиск: индекс элемента " + recursiveIndex);
        } else {
            System.out.println("Рекурсивный поиск: элемент не найден");
        }

        if (iterativeIndex != NOT_FOUND_INDEX) {
            System.out.println("Итеративный поиск: индекс элемента  " + iterativeIndex);
        } else {
            System.out.println("Итеративный поиск: элемент не найден");
        }
    }

    public static void main(String[] args) throws IOException {
        // Объявляем BufferedReader для использования в finally блоке для закрытия
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            //  Ввод
            int size = inputArraySize(reader);
            int[] array = fillArray(reader, size);

            //  Вывод
            printArray(array, "Исходный массив");


            bubbleSort(array);
            printArray(array, "Отсортированный массив");


            int target = inputTargetValue(reader);

            // поиск
            int recursiveIndex = recursiveBinarySearch(array, target, 0, array.length - 1);
            int iterativeIndex = iterativeBinarySearch(array, target);

            // Вывод результатов
            printSearchResults(target, recursiveIndex, iterativeIndex);

        } finally {
            // Обеспечиваем закрытие ресурса BufferedReader
            if (reader != null) {
                reader.close();
            }
        }
    }
}