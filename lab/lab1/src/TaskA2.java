import java.util.Scanner;

// Реализуйте перевод из римских чисел в арабские.
public class TaskA2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter roman number: ");
        String input = scanner.nextLine();

        int n = convertToArabic(input);
        System.out.print("arabic number: " + n);
    }

    public static int convertToArabic(String input) {
        int result = 0;
        int previous = 0;

        for (int i = input.length() - 1; i >= 0; i--) {
            int current = getNumber(input.charAt(i));

            if (current < previous) {
                result -= current;
            } else {
                result += current;
            }

            previous = current;
        }

        return result;
    }

    public static int getNumber(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }
}
