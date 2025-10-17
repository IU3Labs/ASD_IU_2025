package tasks;
import java.util.Scanner;


/*Задание Б3: Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть
число, которое является суммой двух наименьших положительных чисел.*/

public class TaskB3 {
    public static void main(String[] args) {
        int[] numbers = readArray();

        int result = findSum(numbers);

        System.out.println("Сумма двух наименьших положительных чисел: " + result);
    }

    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);

        int size;
        do {
            System.out.print("Введите размер массива (минимум 5 элементов): ");
            size = scanner.nextInt();
            if (size < 5) {
                System.out.println("Массив должен содержать минимум 5 элементов.");
            }
        } while (size < 5);

        int[] array = new int[size];
        System.out.print("Введите " + size + " целых чисел: ");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static int findSum(int[] array) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            int current = array[i];

            if (current > 0) {

                if (current < firstMin) {
                    secondMin = firstMin;
                    firstMin = current;
                }

                else if (current < secondMin && current != firstMin) {
                    secondMin = current;
                }
            }
        }

        if (firstMin == Integer.MAX_VALUE || secondMin == Integer.MAX_VALUE) {
            System.out.println("В массиве недостаточно положительных чисел");
            return -1;
        }

        System.out.println("Первое наименьшее положительное число: " + firstMin);
        System.out.println("Второе наименьшее положительное число: " + secondMin);

        return firstMin + secondMin;
    }

    public static void printArray(int[] array) {
        System.out.print("Массив: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
