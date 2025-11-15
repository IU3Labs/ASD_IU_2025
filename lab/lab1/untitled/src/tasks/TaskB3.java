package tasks;

import static ArrayFunc.readArray;


/*Задание Б3: Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть
число, которое является суммой двух наименьших положительных чисел.*/

public class TaskB3 {
    public static void main(String[] args) {
        int[] numbers = readArray();

        int result = findSum(numbers);
        System.out.println("Сумма двух наименьших положительных чисел: " + result);
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
                } else if (current < secondMin) {
                    secondMin = current;
                }
            }
        }

        if (secondMin == Integer.MAX_VALUE) {
            System.out.println("В массиве недостаточно положительных чисел");
            return -1;
        }

        System.out.println("Первое наименьшее положительное число: " + firstMin);
        System.out.println("Второе наименьшее положительное число: " + secondMin);

        return firstMin + secondMin;
    }

}
