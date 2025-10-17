//Дано целое число. Реализуйте метод, который находит N первых простых
//чисел. Используйте алгоритм «Решето Эратосфена».

package groupa;

import java.util.Scanner;

public class Eratosphen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество простых чисел:");
        int n = scanner.nextInt();

        int[] primes = findFirstNPrimes(n);

        System.out.println("Первые " + n + " простых чисел:");
        for (int i = 0; i < primes.length; i++) {
            System.out.print(primes[i] + " ");
        }
        System.out.println();

        scanner.close();
    }

    public static int[] findFirstNPrimes(int n) {
        if (n <= 0) {
            return new int[0];
        }

        int maxSize = calculateUpperBound(n);
        boolean[] isPrime = new boolean[maxSize + 1];

        for (int i = 2; i <= maxSize; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= maxSize; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxSize; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int[] primes = new int[n];
        int count = 0;

        for (int i = 2; i <= maxSize && count < n; i++) {
            if (isPrime[i]) {
                primes[count] = i;
                count++;
            }
        }

        return primes;
    }

    private static int calculateUpperBound(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 2;

        double lnN = Math.log(n);
        double lnLnN = Math.log(lnN);
        double upperBound = n * (lnN + lnLnN - 0.5);

        int result = (int) Math.ceil(upperBound * 1.2);

        return Math.max(result, 10);
    }
}