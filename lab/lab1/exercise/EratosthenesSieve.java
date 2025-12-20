package lab1.exercise;

import lab1.utils.OutputUtils;

import java.util.Scanner;

import lab1.utils.InputManager;

public class EratosthenesSieve {
    public static void main(String[] args) {
        Scanner scan = InputManager.getScanner();
        System.out.println("Введите N: ");
        int n = InputManager.inputTarget(scan);
        OutputUtils.printArray(sieve(n));
    }

    public static int[] sieve(int n) {
        int maxAmount = searchUpperBound(n);
        boolean[] numbers = createSieve(maxAmount);
        applySieve(numbers, maxAmount);
        return collectPrimes(numbers, n, maxAmount);
    }

    private static int searchUpperBound(int n) {
        if (n <= 0) return 0;

        switch (n) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 7;
            case 4:
                return 11;
            case 5:
                return 13;
            case 6:
                return 17;
            default:
                double logn = Math.log(n);
                double loglogn = Math.log(logn);
                double upperBound = n * (logn + loglogn);
                return Math.max((int) Math.ceil(upperBound) + 100, n * 2 + 10);
        }
    }

    private static boolean[] createSieve(int maxAmount) {
        boolean[] array = new boolean[maxAmount];
        for (int i = 2; i < maxAmount; i++) {
            array[i] = true;
        }
        return array;
    }

    private static void applySieve(boolean[] numbers, int maxAmount) {
        for (int i = 2; i * i < maxAmount; i++) {
            if (numbers[i]) {
                for (int j = i * i; j < maxAmount; j += i) {
                    numbers[j] = false;
                }
            }
        }
    }

    private static int[] collectPrimes(boolean[] numbers, int n, int maxAmount) {
        int[] primes = new int[n];
        int count = 0;
        for (int i = 2; i < maxAmount && count < n; i++) {
            if (numbers[i]) {
                primes[count] = i;
                count++;
            }
        }
        return primes;
    }
}