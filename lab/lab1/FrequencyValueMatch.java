import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FrequencyValueMatch {

    // Метод для ввода массива
    public static int[] inputArray() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество элементов массива: ");
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        System.out.println("Введите " + n + " целых чисел:");

        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            arr[i] = Integer.parseInt(reader.readLine());
        }

        return arr;
    }

    //метод для нахождения числа, где значение равно частоте
    public static int findFrequencyValueMatch(int[] arr) {
        int result = -1;

        // Проходим по всем элементам массива
        for (int i = 0; i < arr.length; i++) {
            int currentValue = arr[i];
            int frequency = 0;

            // Подсчитываем частоту текущего значения
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == currentValue) {
                    frequency++;
                }
            }

            // Проверяем условие: значение равно частоте
            if (currentValue == frequency) {
                // Если нашли подходящее число и оно больше текущего результата
                if (currentValue > result) {
                    result = currentValue;
                }
            }
        }

        return result;
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
            int result = findFrequencyValueMatch(array);

            if (result != -1) {
                System.out.println("Найдено число: " + result + " (значение = частоте)");
            } else {
                System.out.println("Результат: -1 (числа не найдены)");
            }

        } catch (Exception e) {
            System.out.println("Ошибка! Проверьте правильность ввода данных.");
        }
    }
}

//группа Б, 1 задача (2 балла)
//Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.