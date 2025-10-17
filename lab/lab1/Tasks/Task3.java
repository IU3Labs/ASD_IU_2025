/*Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */

package Tasks;


import java.util.Scanner;

public class Task3 {
    public static int ArrayFrequency(int[] array) {
        int max = -1;

        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            int k = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == n) k++;
            }
            if (k == n && n > max) {
                max = n;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("введите кол-во элементов в массиве: ");
        int num = scan.nextInt();
        int[] array = new int[num];
        Main.fillArray(array);
        int p = ArrayFrequency(array);
        System.out.println("результат" + p);


    }
}
