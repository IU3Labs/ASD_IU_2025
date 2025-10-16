
import java.util.Scanner;

public class BinaryTranslation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите длину двоичного числа: ");
        int len1 = scan.nextInt();
        int[] number1 = new int[len1];
        int[] helpArr = new int[len1];
        boolean[] answer = new boolean[len1];

        for (int i = 0; i < number1.length; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент двоичного числа: ");
            number1[i] = scan.nextInt();
        }

        System.out.print("Введите число, на которое делим: ");
        double del = scan.nextDouble();

        int degree = 2;
        double num1 = 0;

        for (int i = 0; i < number1.length; i++) {
            helpArr[i] = number1[i];

            for (int j = 0; j < helpArr.length; j++) {
                num1 = num1 + helpArr[j] * Math.pow(2, len1 - j - degree - 1);
            }

            System.out.println(num1);

            if (num1 % del == 0) {
                answer[i] = true;
            } else {
                answer[i] = false;
            }

            degree -= 1;
            num1 = 0;
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }

        scan.close();
    }
}
