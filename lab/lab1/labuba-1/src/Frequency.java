/*
Задание:
    Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */

import java.util.Scanner;

public class Frequency {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = inputArray(scan);
        sortArray(arr);
        processFrequency(arr);

        scan.close();
    }

    static int[] inputArray(Scanner scan) {
        System.out.print("Введите длину массива: ");
        int len = scan.nextInt();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент массива: ");
            arr[i] = scan.nextInt();
        }
        return arr;
    }

    static void sortArray(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int a = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = a;
                }
            }
        }
    }

    static void processFrequency(int[] arr) {
        int number = 0;
        int result = -1;
        int[] answerArr = new int[arr.length];
        answerArr[0] = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == arr[i - 1]) {
                answerArr[number] += 1;
            } else {
                if (Math.abs(arr[i - 1]) == answerArr[number]) {
                    result = Math.max(result, Math.abs(arr[i - 1]));
                }
                number++;
                answerArr[number] = 1;
            }
        }


        if (Math.abs(arr[arr.length - 1]) == answerArr[number]) {
            result = Math.max(result, Math.abs(arr[arr.length - 1]));
        }

        System.out.println(result);
    }
}
