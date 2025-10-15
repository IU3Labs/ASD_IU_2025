import java.util.Scanner;
//1 Реализуйте метод, входными данными которого являются два числа N и M,
//где N – число в десятичной системе исчисления, а M – число в диапазоне от
//2 до 9, основание системы исчисления, в которое надо перевести исходное число.
// Метод должен возвращать строку с преобразованным значением.
public class NumberSystemConverter {

    public static String convertToBase(int N, int M) {
        String result = "";

        if (N == 0) {
            return "0";
        }

        while (N > 0) {
            result = (N % M) + result;
            N = N / M;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите десятичное число, которое нужно перевести в другую систему счисления: ");
        int N = scanner.nextInt();

        System.out.println("Введите основание системы счисления: ");
        int M = scanner.nextInt();

        String converted = convertToBase(N, M);
        System.out.println("Результат: " + converted);
    }
}