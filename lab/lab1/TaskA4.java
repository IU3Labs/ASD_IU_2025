/*
Дано целое число. Реализуйте метод, который находит N первых простых
чисел. Используйте алгоритм «Решето Эратосфена».
 */

package lab1;

public class TaskA4 {
    public static void main(String[] args) {

        outputArray(sieveOfEratosthenes(MainTask.readInt("Введите число N>2: ",true)));
    }

    public static void outputArray(int[] primes) {
        int n = primes.length;
        System.out.println("Первые " + n + " простых чисел:");
        for (int i = 0; i < primes.length; i++) {
            System.out.print(primes[i]);
            if (i < primes.length - 1) {
                System.out.print(", ");
            }
        }
    }
    public static int[] sieveOfEratosthenes(int n) {
        if (n == 0) return new int[0];
        if (n == 1) return new int[]{2};

        int upperBound;
        if (n < 6) {
            upperBound = 20;
        } else {
            upperBound = (int)(n * Math.log(n) + n * Math.log(Math.log(n))) + 10;
        }

        boolean[] isPrime = new boolean[upperBound + 1];
        for (int i = 2; i <= upperBound; i++) {
            isPrime[i] = true;
        }

        for (int p = 2; p * p <= upperBound; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= upperBound; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= upperBound; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        int[] primes = new int[Math.min(n, count)];
        int index = 0;
        for (int i = 2; i <= upperBound && index < primes.length; i++) {
            if (isPrime[i]) {
                primes[index++] = i;
            }
        }

        return primes;
    }
}