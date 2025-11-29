/*
Задание:
    Дан массив целых чисел, представляющий двоичное число.
    Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
    выглядит так: arr = [1, 3, 6]. То есть:
    • arr[0] = bi_arr[0] = 1! = 1"#,
    • arr[1] = bi_arr[0] bi_arr[1] =11! = 3"#,
    • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] =110! = 6"#
    Так же дано целое положительное число – n. Вернуть массив Boolean, где
    true – число делится на N, false – нет.
    Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
    так: [false, false, true].
 */
import java.util.Scanner;

public class BinaryTranslation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] number1 = inputBinaryNumber(scan);
        double del = inputDivisor(scan);
        boolean[] answer = processBinaryNumber(number1, del);
        printResults(answer);

        scan.close();
    }

    static int[] inputBinaryNumber(Scanner scan) {
        System.out.print("Введите длину двоичного числа: ");
        int len1 = scan.nextInt();
        int[] number1 = new int[len1];

        for (int i = 0; i < number1.length; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент двоичного числа: ");
            number1[i] = scan.nextInt();
        }
        return number1;
    }

    static double inputDivisor(Scanner scan) {
        System.out.print("Введите число, на которое делим: ");
        return scan.nextDouble();
    }

    static boolean[] processBinaryNumber(int[] number1, double del) {
        int len1 = number1.length;
        int[] helpArr = new int[len1];
        boolean[] answer = new boolean[len1];
        int degree = 2;
        double num1 = 0;

        for (int i = 0; i < number1.length; i++) {
            helpArr[i] = number1[i];

            for (int j = 0; j < helpArr.length; j++) {
                num1 = num1 + helpArr[j] * Math.pow(2, len1 - j - degree - 1);
            }

            if (num1 % del == 0) {
                answer[i] = true;
            } else {
                answer[i] = false;
            }

            degree -= 1;
            num1 = 0;
        }
        return answer;
    }

    static void printResults(boolean[] answer) {
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}