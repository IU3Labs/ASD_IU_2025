import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SumTwoSmallest {

    // Метод для ввода массива
    public static int[] inputArray() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n;
        while (true) {
            System.out.print("Введите количество элементов массива (минимум 5): ");
            String input = reader.readLine();
            n = Integer.parseInt(input);

            if (n >= 5) {
                break;
            } else {
                System.out.println("Ошибка! Минимальное количество элементов - 5. Попробуйте снова.");
            }
        }

        int[] arr = new int[n];
        System.out.println("Введите " + n + " целых чисел:");

        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            arr[i] = Integer.parseInt(reader.readLine());
        }

        return arr;
    }

    // Метод для нахождения суммы двух наименьших положительных чисел
    public static int sumTwoSmallestPositive(int[] arr) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            // Ищем только положительные числа
            if (arr[i] > 0) {
                if (arr[i] < firstMin) {
                    secondMin = firstMin;
                    firstMin = arr[i];
                } else if (arr[i] < secondMin) {
                    secondMin = arr[i];
                }
            }
        }

        // Проверяем, найдены ли два положительных числа
        if (firstMin == Integer.MAX_VALUE) {
            System.out.println("Положительные числа не найдены");
            return -1;
        } else if (secondMin == Integer.MAX_VALUE) {
            System.out.println("Найдено только одно положительное число: " + firstMin);
            return -1;
        }

        return firstMin + secondMin;
    }

    public static void main(String[] args) {
        try {
            // Ввод массива
            int[] array = inputArray();

            // Вывод исходного массива
            System.out.print("\nВведенный массив: ");
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();

            // Вычисление и вывод результата
            int result = sumTwoSmallestPositive(array);
            if (result != -1) {
                System.out.println("Сумма двух наименьших положительных чисел: " + result);
            }

        } catch (Exception e) {
            System.out.println("Ошибка! Проверьте правильность ввода данных.");
        }
    }
}

//группа Б, 3я задача (2 балла)
//Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.