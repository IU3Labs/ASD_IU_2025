// Текст задачи: Дано целое число N. Реализуйте метод, который находит
// N первых простых чисел. Используйте алгоритм «Решето Эратосфена». 

public class SieveOfEratosthenes {

    public static int[] findFirstNPrimes(int n) {
        if (n <= 0) {
            return new int[0];
        }

        int[] primes = new int[n];
        int count = 0;

        int limit = n * 20;
        if (n < 10) limit = 100;

        while (count < n) {
            boolean[] isPrime = new boolean[limit + 1];
            for (int i = 2; i <= limit; i++) {
                isPrime[i] = true;
            }

            for (int p = 2; p * p <= limit; p++) {
                if (isPrime[p]) {
                    for (int i = p * p; i <= limit; i += p) {
                        isPrime[i] = false;
                    }
                }
            }

            count = 0;
            for (int p = 2; p <= limit && count < n; p++) {
                if (isPrime[p]) {
                    primes[count] = p;
                    count++;
                }
            }

            if (count < n) {
                limit *= 2;
                int[] newPrimes = new int[n];
                primes = newPrimes;
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int n = LabHelper.readInt("Введите N (количество простых чисел): ");
        int[] firstNPrimes = findFirstNPrimes(n);

        System.out.println(n + " первых простых чисел:");
        LabHelper.printArray(firstNPrimes);
    }
}