package lab1.a;

/* 4. Дано целое число. Реализуйте метод, который находит N первых простых
чисел. Используйте алгоритм «Решето Эратосфена». */

import java.util.Scanner;

public class EratoshenesSieve { static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int N = input.nextInt();
        printArray(findPrimeNumbers(N));
    }

    static int[] findPrimeNumbers(int N) {
        int upLim;
        if (N <= 20) {
            upLim = N * 5;
        } else {
            upLim = (int) Math.round(N * (Math.log(N) + Math.log(Math.log(N)) - 0.5));
        }
        int[] sieve = new int[upLim];
        int[] result = new int[N];
        for (int i = 2; i * i < upLim; i++) {
            for (int j = i * i; j < upLim; j += i) {
                sieve[j] -= 1;
            }
        }
        int assignedValuesCount = 0;
        for (int i = 2; i < sieve.length & assignedValuesCount < N; i++) {
            if (sieve[i] == 0) {
                result[assignedValuesCount] = i;
                assignedValuesCount++;
            }
        }
        return result;
    }

    static void printArray(int[] array) {
        for (int j = 0; j < array.length - 1; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.print(array[array.length - 1]);
    }
}
