//4 Дано целое число. Реализуйте метод, который находит N первых простых
//        чисел. Используйте алгоритм «Решето Эратосфена».
package tasks;

import utils.OutputUtils;
import java.util.Scanner;
import utils.InputManager;

public class EratosthenesSieve {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите N: ");
        int n = InputManager.inputTarget(scan);
        OutputUtils.printArray(sieve(n));
        scan.close();
    }

    public static int[] sieve(int n){
        int maxAmount = n * 15;
        boolean[] numbers = createSieve(maxAmount);
        applySieve(numbers, maxAmount);
        return collectPrimes(numbers, n, maxAmount);
    }

    private static boolean[] createSieve(int maxAmount){
        boolean[] array = new boolean[maxAmount];
        for (int i = 2; i < maxAmount; i++){
            array[i] = true;
        }
        return array;
    }

    private static void applySieve(boolean[] numbers, int maxAmount){
        for (int i = 2; i * i < maxAmount; i++){
            if (numbers[i]){
                for (int j = i * i; j < maxAmount; j += i){
                    numbers[j] = false;
                }
            }
        }
    }

    private static int[] collectPrimes(boolean[] numbers, int n, int maxAmount){
        int[] primes = new int[n];
        int count = 0;
        for (int i = 2; i < maxAmount && count < n; i++){
            if (numbers[i]){
                primes[count] = i;
                count++;
            }
        }
        return primes;
    }
}