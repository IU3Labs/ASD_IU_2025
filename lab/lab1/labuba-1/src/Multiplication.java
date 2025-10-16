
import java.util.Scanner;

public class Multiplication {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите длину первого числа: ");
        int len1 = scan.nextInt();
        int[] number1 = new int[len1];
        for (int i = 0; i < len1; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент числа: ");
            number1[i] = scan.nextInt();
        }

        System.out.print("Введите длину второго числа: ");
        int len2 = scan.nextInt();
        int[] number2 = new int[len2];
        for (int i = 0; i < len2; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент числа: ");
            number2[i] = scan.nextInt();
        }

        double num1 = 0;
        int degree = 0;
        for (int i = number1.length - 1; i >= 0; i--) {
            num1 = num1 + number1[i] * Math.pow(10, degree);
            degree += 1;
        }

        double num2 = 0;
        degree = 0;
        for (int i = number2.length - 1; i >= 0; i--) {
            num2 = num2 + number2[i] * Math.pow(10, degree);
            degree += 1;
        }

        double answer = num1 * num2;
        System.out.println("Результат умножения: " + answer);

        scan.close();
    }
}
