//Реализуйте метод, входными данными которого являются два числа N и M,
//где N – число в десятичной системе исчисления, а M – число в диапазоне от
//2 до 9, основание системы исчисления, в которое надо перевести исходное
//число. Метод должен возвращать строку с преобразованным значением.

package Tasks;

import java.util.Scanner;

//  задание исправлено полностью

public class Task1 {
    public static int readDecimalNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите число которое хотите перевести: ");
        return scan.nextInt();
    }

    public static int readBase() {
        Scanner scan = new Scanner(System.in);
        int base;
        do {
            System.out.print("Введите новую систему счисления (2-9): ");
            base = scan.nextInt();
            if (base < 2 || base > 9) {
                System.out.println("Ошибка: основание от 2 до 9!");
            }
        } while (base < 2 || base > 9);

        return base;
    }

    public static String convertBase(int N, int M) {
        if (N == 0) {
            return "0";
        }

        String result = "";
        while (N > 0) {
            int remainder = N % M;
            result = result + remainder;
            N /= M;
        }

        String reversed = "";
        for (int i = result.length() - 1; i >= 0; i--) {
            reversed = reversed + result.charAt(i);
        }
        return reversed;
    }


    public static void printResult(String result) {
        System.out.println("Ваше число в новой системе счисления: " + result);
    }

    public static void main(String[] args) {
        int N = readDecimalNumber();
        int M = readBase();

        String result = convertBase(N, M);

        printResult(result);
    }
}

