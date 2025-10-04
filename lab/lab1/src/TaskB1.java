import java.util.Scanner;

/*
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
*/

public class TaskB1 {

    public static void task1(String[] args) {
        //Ввод и вывод
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a length of the array: \n");
        int n = scanner.nextInt();


        int[] array = new int[n];
        System.out.println("Input elements of the array: \n");
        for (int i = 0; i < n; i++) array[i] = scanner.nextInt();

        int result = -1;

        for (int i = 0; i < n; i++) {
            int current = array[i];
            if (current > n) continue;
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (current == array[j]) count++;
            }
            if (current == count) result = Math.max(result, current);
        }
        System.out.println(result);
    }
}