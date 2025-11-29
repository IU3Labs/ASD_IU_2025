//1 Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

package b;

import java.util.Scanner;

public class FrequentNumberFinder {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] arr = inputArr();
        System.out.println("Число: " + findNumber(arr));
        scanner.close();
    }

    public static int[] inputArr(){
        System.out.println("Введите размер массива: ");
        int length = scanner.nextInt();
        int[] arr = new int[length];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < length; i++){
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static int findNumber(int[] arr) {
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            int currentNumber = arr[i];
            int count = 0;

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == currentNumber) {
                    count++;
                }
            }

            if (currentNumber == count && count > result) {
                result = currentNumber;
            }

        }

        return result;
    }

}