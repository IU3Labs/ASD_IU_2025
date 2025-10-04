package lab1;

import java.util.Scanner;

/*
Группа А. Задание 4.
Дано целое число. Реализуйте метод, который находит N первых простых
чисел. Используйте алгоритм «Решето Эратосфена».
*/

public class Task4Main {
    public static int[] findFirstPrimes(int n) {
        if (n <= 0) {
            return new int[0];
        }
        int limit = estimateUpperBound(n);
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int[] primes = new int[n];
        int count = 0;
        for (int i = 2; i <= limit && count < n; i++) {
            if (isPrime[i]) {
                primes[count] = i;
                count++;
            }
        }
        return primes;
    }

    private static int estimateUpperBound(int n) {
        if (n < 6) {
            return 15;
        }
        return (int) (n * (Math.log(n) + Math.log(Math.log(n)))) + 10;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество простых чисел (N): ");
        int n = scanner.nextInt();
        int[] primes = findFirstPrimes(n);
        System.out.println("Первые " + n + " простых чисел:");
        printArray(primes);
        scanner.close();
    }
}
