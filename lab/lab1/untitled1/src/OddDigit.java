import java.util.Scanner;

public class OddDigit {

    public static String addHyphens(int number) {
        String numStr = String.valueOf(number);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numStr.length(); i++) {
            char digit = numStr.charAt(i);

            if (Character.isDigit(digit) && (digit - '0') % 2 != 0) {
                if (i > 0) {
                    result.append('-');
                }
                result.append(digit);
                result.append('-');
            } else {
                result.append(digit);
            }
        }

        if (result.length() > 0 && result.charAt(0) == '-') {
            result.deleteCharAt(0);
        }
        if (result.length() > 0 && result.charAt(result.length() - 1) == '-') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        int number = scanner.nextInt();

        String result = addHyphens(number);

        System.out.println("Результат: " + result);
    }
}
