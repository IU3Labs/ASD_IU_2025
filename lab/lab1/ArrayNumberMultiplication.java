import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrayNumberMultiplication {

    // Метод для ввода массива-числа
    public static int[] inputArray(String numberName) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество цифр в " + numberName + ": ");
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        System.out.println("Введите " + n + " цифр числа (слева направо):");

        for (int i = 0; i < n; i++) {
            System.out.print("Цифра " + (i + 1) + ": ");
            int digit = Integer.parseInt(reader.readLine());
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("Цифра должна быть от 0 до 9");
            }
            arr[i] = digit;
        }

        return arr;
    }

    // Метод для преобразования массива в число
    public static int arrayToNumber(int[] arr) {
        int number = 0;
        for (int i = 0; i < arr.length; i++) {
            number = number * 10 + arr[i];
        }
        return number;
    }

    // Метод для преобразования числа в массив цифр
    public static int[] numberToArray(int number) {
        if (number == 0) {
            return new int[]{0};
        }

        // Считаем количество цифр
        int temp = number;
        int length = 0;
        while (temp > 0) {
            temp /= 10;
            length++;
        }

        // Создаем массив и заполняем цифрами
        int[] result = new int[length];
        temp = number;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = temp % 10;
            temp /= 10;
        }

        return result;
    }

    // Основной метод умножения двух чисел, представленных массивами
    public static int[] multiplyArrays(int[] arr1, int[] arr2) {
        // Преобразуем массивы в числа
        int num1 = arrayToNumber(arr1);
        int num2 = arrayToNumber(arr2);

        // Умножаем числа
        int result = num1 * num2;

        // Преобразуем результат обратно в массив
        return numberToArray(result);
    }

    // Метод для вывода массива
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        try {
            // Ввод первого числа
            int[] number1 = inputArray("первом числе");
            System.out.print("Первое число: ");
            printArray(number1);

            // Ввод второго числа
            int[] number2 = inputArray("втором числе");
            System.out.print("Второе число: ");
            printArray(number2);

            // Умножение
            int[] result = multiplyArrays(number1, number2);

            // Вывод результата
            System.out.print("\nРезультат умножения: ");
            printArray(result);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

//группа Б, 2я задача (2 балла)
//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.