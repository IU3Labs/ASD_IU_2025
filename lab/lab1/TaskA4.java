// Дано целое число. Реализуйте метод, который находит N первых простых чисел. Используйте алгоритм «Решето Эратосфена».
import java.util.Scanner;

public class TaskA4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество простых чисел: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Ошибка");
            return;
        }


        int[] primes = findFirstNPrimes(n);
        for (int i = 0; i < primes.length; i++) {
            System.out.print(primes[i] + " ");
        }

        scanner.close();
    }

    public static int[] findFirstNPrimes(int n) {
        if (n <= 0) {
            return new int[0];
        }

        // оценка верхней границы
        int limit;
        if (n < 6) {
            limit = 20;
        } else {
            limit = (int)(n * Math.log(n) * 2) + 100;
        }

        boolean[] isPrime = new boolean[limit + 1];
        int[] primes = new int[n];
        int count = 0;

        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // решето эратосфена
        for (int i = 2; i <= limit && count < n; i++) {
            if (isPrime[i]) {
                primes[count++] = i;
                for (int j = i * i; j <= limit && j > 0; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return primes;
    }

}
