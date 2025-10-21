// Task:Пусть любое число – это массив его цифр слева направо.
// Пример, число 1234 – это массив [1,2,3,4]. Дан массив целых чисел.
// Реализовать умножение двух чисел. Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
// Результат – число, представленное массивом
package lab1;

public class MultiplyArrays {
    public static void main(String[] args) {
        int[] number1 = {1, 2, 3, 4};
        int[] number2 = {1, 1};

        int[] result = multiplyNumbers(number1, number2);

        System.out.print("Результат: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i != result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    private static int[] multiplyNumbers(int[] num1, int[] num2) {
        int n = num1.length;
        int m = num2.length;
        int[] product = new int[n + m]; // Максимальная длина результата

        // Перемножаем каждую цифру
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int temp = num1[i] * num2[j] + product[i + j + 1];
                product[i + j + 1] = temp % 10;
                product[i + j] += temp / 10;
            }
        }
        int startIndex = 0;
        while (startIndex < product.length - 1 && product[startIndex] == 0) {
            startIndex++;
        }
        int[] result = new int[product.length - startIndex];
        for (int i = startIndex; i < product.length; i++) {
            result[i - startIndex] = product[i];
        }
        return result;
    }
}
