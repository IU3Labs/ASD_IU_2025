/*Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное*/

import java.util.Scanner;

public class NumberBaseConverter {

    public static String convertToBase(int number, int base) {
        if (number == 0) {
            return "0";
        }

        boolean isNegative = number < 0;
        if (isNegative) {
            number = -number;
        }

        String result = "";

        while (number > 0) {
            int remainder = number % base;
            result = remainder + result;
            number = number / base;
        }

        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number in decimal system: ");
        int number = scanner.nextInt();

        System.out.print("Enter target base (2-9): ");
        int base = scanner.nextInt();

        String result = convertToBase(number, base);
        System.out.println(number + " in base " + base + " = " + result);

        scanner.close();
    }
}