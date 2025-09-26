import java.util.Scanner;

public class MaxNumberFromDigits {

    public static int getMaxNumber(int number) {
        String numStr = String.valueOf(number);

        char[] digits = numStr.toCharArray();

        for (int i = 0; i < digits.length - 1; i++) {
            for (int j = 0; j < digits.length - 1 - i; j++) {
                if (digits[j] > digits[j + 1]) {
                    char temp = digits[j];
                    digits[j] = digits[j + 1];
                    digits[j + 1] = temp;
                }
            }
        }

        StringBuilder result = new StringBuilder(new String(digits));
        result.reverse();

        return Integer.parseInt(result.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        int number = scanner.nextInt();

        int maxNumber = getMaxNumber(number);

        System.out.println("Максимальное число: " + maxNumber);
    }
}
