/*
Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел.
 */

package lab1;

public class TaskB3 {
    public static void main(String[] args) {
        System.out.println(counterArray(MainTask.readArray()));
    }
    private static int counterArray(int[] array) {
        if (array == null || array.length < 5) {
            throw new IllegalArgumentException("Массив должен содержать минимум 5 элементов");
        }

        int minFirst = Integer.MAX_VALUE;
        int minSecond = Integer.MAX_VALUE;
        int positiveCount = 0;

        for (int number : array) {
            if (number > 0) {
                positiveCount++;
                if (number <= minFirst) {
                    minSecond = minFirst;
                    minFirst = number;
                } else if (number < minSecond || minSecond == minFirst) {
                    minSecond = number;
                }
            }
        }
        if (positiveCount < 2) {
            throw new IllegalArgumentException("Должно быть минимум 2 положительных числа");
        }
        if (minFirst > Integer.MAX_VALUE - minSecond) {
            throw new ArithmeticException("Переполнение при сложении");
        }

        return minFirst + minSecond;
    }
}
