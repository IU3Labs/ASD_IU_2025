// Реализуйте метод, входными данными которого являются два числа N и M, где N – число в десятичной системе исчисления, а M – число в диапазоне от 2 до 9, основание системы исчисления, в которое надо перевести исходное
import java.util.Scanner;

public class TaskA1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(convertNumber(n, m));
    }

    // конвертируем число
    public static String convertNumber(int n, int m) {
        if (m < 2 || m > 9) {
            throw new IllegalArgumentException("Неверное основание системы счисления");
        }
        if (n == 0) {
            return "0";
        }
        boolean isNegative = n < 0;
        int number = Math.abs(n);
        StringBuilder result = new StringBuilder();

        // заполняем строку
        while (number > 0) {
            int remainder = number % m;
            result.insert(0, remainder);
            number = number / m;
        }
        if (isNegative) {
            result.insert(0, '-');
        }

        return result.toString();
    }

}
