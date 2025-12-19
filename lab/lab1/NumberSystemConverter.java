import java.util.Scanner;

// 1. Реализуйте метод, входными данными которого являются два числа:
// decimalNumber – число в десятичной системе,
// base – основание системы счисления (от 2 до 9).
// Метод возвращает строку с преобразованным значением.
public class NumberSystemConverter {

    public static String convertToBase(int decimalNumber, int base) {
        String result = "";

        if (decimalNumber == 0) {
            return "0";
        }

        while (decimalNumber > 0) {
            result = (decimalNumber % base) + result;
            decimalNumber = decimalNumber / base;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите десятичное число, которое нужно перевести: ");
        int decimalNumber = scanner.nextInt();

        System.out.print("Введите основание системы счисления (2–9): ");
        int base = scanner.nextInt();

        String converted = convertToBase(decimalNumber, base);
        System.out.println("Результат: " + converted);

        scanner.close();
    }
}