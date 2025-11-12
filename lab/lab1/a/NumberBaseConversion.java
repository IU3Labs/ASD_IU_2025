//Реализуйте метод, входными данными которого являются два числа N и M,
//где N – число в десятичной системе исчисления, а M – число в диапазоне от
//2 до 9, основание системы исчисления, в которое надо перевести исходное число. Метод должен возвращать строку с преобразованным значением.

package a;

import java.util.Scanner;

public class NumberBaseConversion {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Введите число N в десятичной системе: ");
        int N = scanner.nextInt();
        System.out.print("Введите основание системы M (2-9): ");
        int M = scanner.nextInt();

        if (M < 2 || M > 9) {
            System.out.println("Ошибка: основание системы должно быть от 2 до 9");
            return;
        }

        String result = convertToBase(N, M);
        System.out.println("Число " + N + " в системе с основанием " + M + " = " + result);

        scanner.close();
    }


    public static String convertToBase(int N, int M) {

        if (N == 0) {
            return "0";
        }

        boolean isNegative = N < 0;
        if (isNegative) {
            N = -N;
        }

        String result = "";

        while (N > 0) {
            int remainder = N % M;
            result = remainder + result;
            N /= M;
        }

        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }




}
