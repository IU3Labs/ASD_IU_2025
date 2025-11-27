import java.util.Scanner;

// Пусть любое число – это массив его цифр слева направо. Дан массив целых чисел. Реализовать умножение двух чисел. Результат – число, представленное массивом.
class GroupBTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество цифр для первого числа: ");
        int m = scanner.nextInt();
        int[] num1 = ArrayUtility.fillArray(scanner, m, "Введите цифры первого числа (от старшего к младшему):");
        System.out.print("Введите количество цифр для второго числа: ");
        int n = scanner.nextInt();
        int[] num2 = ArrayUtility.fillArray(scanner, n, "Введите цифры второго числа (от старшего к младшему):");
        int[] result = multiply(num1, num2);
        ArrayUtility.printArray(result);
    }

    private static int[] multiply(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;
        int[] rev1 = new int[m];
        for (int i = 0; i < m; i++) {
            rev1[i] = num1[m - 1 - i];
        }
        int[] rev2 = new int[n];
        for (int i = 0; i < n; i++) {
            rev2[i] = num2[n - 1 - i];
        }
        int[] result = new int[m + n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i + j] += rev1[i] * rev2[j];
            }
        }
        int carry = 0;
        for (int i = 0; i < m + n; i++) {
            int sum = result[i] + carry;
            result[i] = sum % 10;
            carry = sum / 10;
        }
        int[] finalResult = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            finalResult[i] = result[m + n - 1 - i];
        }
        int start = 0;
        while (start < m + n - 1 && finalResult[start] == 0) {
            start++;
        }
        int[] trimmed = new int[m + n - start];
        for (int i = 0; i < trimmed.length; i++) {
            trimmed[i] = finalResult[start + i];
        }
        return trimmed;
    }
}