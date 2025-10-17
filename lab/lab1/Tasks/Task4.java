/*Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
 */

package Tasks;


import java.util.Scanner;

public class Task4 {
    public static int[] ArraySummury(int[] array1, int[] array2) {
        int num1 = 0;
        for (int i = 0; i < array1.length; i++) {
            num1 = num1 * 10 + array1[i];
        }

        int num2 = 0;
        for (int i = 0; i < array2.length; i++) {
            num2 = num2 * 10 + array2[i];
        }
        int rez = num1 * num2;
        String s = String.valueOf(rez);
        int[] rezz = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            rezz[i] = s.charAt(i) - '0';
        }

        return rezz;

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("введите кол- во элементов в массиве 1: ");
        int num1 = scan.nextInt();
        int[] array1 = new int[num1];
        Main.fillArray(array1);
        System.out.print("введите кол- во элементов в массиве 2: ");
        int num2 = scan.nextInt();
        int[] array2 = new int[num2];
        Main.fillArray(array2);
        int[] rezz = ArraySummury(array1, array2);
        Main.arrayOutput(rezz);


    }
}
