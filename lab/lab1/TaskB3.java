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
        int minFirst = array[0];
        int minSecond = array[0];

        for (int number : array) {
            if (number > 0) {
                if (number < minFirst || minFirst <= 0) {
                    minSecond = minFirst;
                    minFirst = number;
                } else if (number < minSecond || minSecond <= 0) {
                    minSecond = number;
                }
            }
        }

        return minFirst+minSecond;
    }
}
