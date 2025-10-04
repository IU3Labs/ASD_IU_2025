import java.util.Scanner;

public class Task1GroupA {
    public static void main(String[] args) {
        System.out.println("Введите десятичное число: ");
        int inputDecimalNumber = inputNumber();
        System.out.println("Введите новую систему счисления(от 2 до 9): ");
        int inputNewBase = inputNumber();
        convertToBase(inputDecimalNumber,inputNewBase);
    }

    public static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void convertToBase(int decimalNumber, int newBase) {
        if (newBase < 2 || newBase > 9) {
            System.out.println("Ошибка: основание системы счисления должно быть от 2 до 9");
            return;
        }
        if (decimalNumber == 0) {
            System.out.println(decimalNumber);
            return;
        }
        StringBuilder result = new StringBuilder();
        while (decimalNumber > 0) {
            int remainder = decimalNumber % newBase;
            result.insert(0, remainder);
            decimalNumber = decimalNumber / newBase;
        }
        System.out.println("Число в указанной системе счисления: " + result);
    }
}
/*
1 Реализуйте метод, входными данными которого являются два числа N и M,
где N – число в десятичной системе исчисления, а M – число в диапазоне от
2 до 9, основание системы исчисления, в которое надо перевести исходное число.
Метод должен возвращать строку с преобразованным значением.
 */
