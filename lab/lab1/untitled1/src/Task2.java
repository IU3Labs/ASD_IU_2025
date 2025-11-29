// Группа Б. Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtils.inputArray(scanner);

        if (array.length < 5) {
            System.out.println("Требуется минимум 5 элементов!");
            return;
        }

        int result = findSmallestPositiveSum(array);

        if (result == -1) {
            System.out.println("Не найдено двух положительных чисел");
        } else {
            System.out.println("Сумма двух наименьших положительных: " + result);
        }
    }

    public static int findSmallestPositiveSum(int[] array) {
        int firstMin = 0;
        int secondMin = 0;

        for (int i = 0; i < array.length; i++) {
            int current = array[i];

            if (current > 0) {
                if (firstMin == 0) {
                    firstMin = current;
                } else if (secondMin == 0) {
                    secondMin = current;
                } else if (current < firstMin) {
                    secondMin = firstMin;
                    firstMin = current;
                } else if (current < secondMin) {
                    secondMin = current;
                }
            }
        }

        if (secondMin == 0) {
            return -1;
        }

        return firstMin + secondMin;

    }
}


