//Реализовать алгоритм бинарного поиска двумя способами.
//Итерационный метод
import java.util.Scanner;

public class BinarySearchIterative {
    public static int findPosition(int num_list[], int number) {
        int first = 0;
        int last = num_list.length - 1;
        while (first <= last) {
            int middle = first + (last - first) / 2;
            if (num_list[middle] == number) {
                System.out.println("Индекс числа " + number + " в массиве: " + middle);
                return middle;
            } else if (num_list[middle] < number) {
                first = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        System.out.println("Число " + number + " не найдено");
        return -1;
    }

    public static void arrayFill(int[] array, int n){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Обмен элементов
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        arrayFill(array, n);

        System.out.print("Введите искомое число: ");
        int number = scanner.nextInt();

        System.out.print("Исходный массив: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        bubbleSort(array);
        System.out.print("Отсортированный массив: ");
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        int result = findPosition(array, number);
        scanner.close();
    }
}