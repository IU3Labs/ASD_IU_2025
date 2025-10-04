/*
Дано целое число. Реализуйте метод, который находит N первых простых
чисел. Используйте алгоритм «Решето Эратосфена».
 */

package lab1;

public class TaskA4 {
    public static void main(String[] args) {
        sieveOfEratosthenes(MainTask.readInt("Введите число N>2: ",true));
    }

    private static void sieveOfEratosthenes(int size) {
        boolean[] array = new boolean[size+1];
        for (int i = 0; i < size; i++) {
            array[i] = true;
        }
        array[0] = false;
        array[1] = false;
        for (int number = 2; number*number <= size; number++) {
            if (array[number]) {
                for (int i = number*number; i <= size; i += number) {
                    array[i] = false;
                }
            }
        }
        System.out.print("Простые числа до " + size + ": ");
        for (int i = 2; i <= size; i++) {
            if (array[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
