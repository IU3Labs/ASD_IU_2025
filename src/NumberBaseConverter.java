import java.util.Scanner;

public class NumberBaseConverter {

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


    public static String convertToBase(int number, int base) {
        if (number == 0) {
            return "0";
        }

        boolean isNegative = number < 0;
        if (isNegative) {
            number = -number;
        }

        StringBuilder result = new StringBuilder();

        while (number > 0) {
            int remainder = number % base;
            result.insert(0, remainder);
            number = number / base;
        }

        if (isNegative) {
            result.insert(0, "-");
        }

        return result.toString();
    }
}