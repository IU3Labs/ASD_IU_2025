// Группа Б. Задача 4.
// Дан массив целых чисел, представляющий двоичное число.
// Пример: bi_arr = [1, 1, 0]  префиксы: "1"=1, "11"=3, "110"=6.
// Дано целое положительное число n.
// Вернуть массив Boolean, где true – префикс делится на n, false – нет.

import java.util.Scanner;

public class StrangeBinTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] binArr = readBinaryArray(scanner);
        int n = readDivisor(scanner);
        int[] decimalPrefixes = convertPrefixesToDecimal(binArr);
        boolean[] result = checkDivisibility(decimalPrefixes, n);
        printResults(result);
        scanner.close();
    }


    private static int[] readBinaryArray(Scanner scanner) {
        System.out.println("Введите число элементов массива:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите " + n + " битов (0 или 1):");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }


    private static int readDivisor(Scanner scanner) {
        System.out.println("Введите делитель n:");
        return scanner.nextInt();
    }


    private static int[] convertPrefixesToDecimal(int[] binArr) {
        int n = binArr.length;
        int[] decimals = new int[n];
        int current = 0;
        for (int i = 0; i < n; i++) {
            current = current * 2 + binArr[i];
            decimals[i] = current;
        }
        return decimals;
    }


    private static boolean[] checkDivisibility(int[] decimals, int n) {
        boolean[] result = new boolean[decimals.length];
        for (int i = 0; i < decimals.length; i++) {
            result[i] = (decimals[i] % n == 0);
        }
        return result;
    }


    private static void printResults(boolean[] boolArr) {
        for (boolean b : boolArr) {
            System.out.println(b);
        }
    }
}