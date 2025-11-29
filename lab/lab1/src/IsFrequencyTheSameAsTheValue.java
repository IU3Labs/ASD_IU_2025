// Группа Б, Задача 1
// Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

import java.util.Scanner;

public class IsFrequencyTheSameAsTheValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Utils.readArray(scanner);
        int[] coin = checkCoincidence(arr);
        printResults(coin);
        scanner.close();
    }


    private static int[] checkCoincidence(int[] arr) {
        int numofelements = arr[0];
        int[] coin = new int[numofelements + 1];
        coin[0] = numofelements;
        for (int i = 1; i <= numofelements; i++) {
            coin[i] = -1;
        }

        for (int i = 1; i <= numofelements; i++) {
            int num = arr[i];
            int total = 0;
            for (int j = 1; j <= numofelements; j++) {
                if (arr[j] == num) {
                    total += 1;
                }
            }
            if (total == num) {
                coin[i] = num;
            }
        }

        return coin;
    }


    private static void printResults(int[] coin) {
        int maxvalue = -1;

        for (int i = 1; i <= coin[0]; i++) {
            if (coin[i] > maxvalue) {
                maxvalue = coin[i];
            }
        }

        System.out.println(maxvalue);
    }
}
