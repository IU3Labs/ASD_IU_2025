package lab.lab1.a;
/* 4. Дано целое число. Реализуйте метод, который находит N первых простых
чисел. Используйте алгоритм «Решето Эратосфена». */
import java.util.Scanner;
import lab.lab1.ArrUtility;

public class EratoshenesSieve {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n = input.nextInt();
        ArrUtility.printArray(findPrimeNumbers(n));
    }

    static int[] findPrimeNumbers(int n) {
        int upLimit = calculateUpperLimit(n);
        boolean[] isPrime = createSieve(upLimit);
        return extractPrimes(isPrime, n);
    }

    static int calculateUpperLimit(int n) {
        if (n <= 20) {
            return n * 5;
        } else {
            return (int) Math.round(n * (Math.log(n) + Math.log(Math.log(n)) - 0.5));
        }
    }

    static boolean[] createSieve(int upLimit) {
        boolean[] isPrime = new boolean[upLimit];
        for (int i = 2; i < upLimit; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < upLimit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < upLimit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    static int[] extractPrimes(boolean[] isPrime, int n) {
        int[] primes = new int[n];
        int count = 0;

        for (int i = 2; i < isPrime.length && count < n; i++) {
            if (isPrime[i]) {
                primes[count] = i;
                count++;
            }
        }

        return primes;
    }
}
