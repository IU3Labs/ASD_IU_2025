//Task: Дано целое число. Реализуйте метод, который находит N первых простых чисел.
// Используйте алгоритм «Решето Эратосфена»
package lab1;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        int n = 10;
        int[] primes = findFirstNPrimes(n);

        System.out.print("Первые " + n + " простых чисел: [");
        for (int i = 0; i < primes.length; i++) {
            System.out.print(primes[i]);
            if (i != primes.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    private static int[] findFirstNPrimes(int n) {
        if (n <= 0) {
            return new int[0];
        }
        int estimateSize = n * 10;
        boolean[] isComposite = new boolean[estimateSize]; // true — составное
        int count = 0;
        int[] primes = new int[n];

        for (int i = 2; i < estimateSize && count < n; i++) {
            if (!isComposite[i]) {
                primes[count] = i;
                count++;
                for (int j = i * 2; j < estimateSize; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        return primes;
    }
}
