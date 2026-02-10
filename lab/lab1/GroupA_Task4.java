/*
Группа А. Алгоритмические задачи
Дано целое число. Реализуйте метод, который находит N первых простых чисел.
Используйте алгоритм "Решето Эратосфена"
 */

import java.util.Scanner;

public class GroupA_Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество простых чисел: ");
        int quanPrime = sc.nextInt();

        int n = quanAll(quanPrime);

        int[] resheto = getPrimeMas(n);

        displayMassive(resheto, quanPrime);

    }

    // Get guaranteed quantity of numbers to get quanPrime primes
    public static int quanAll(int quanPrime) {
        int n = 2;
        do {
            n++;
        } while (n/Math.log(n) < quanPrime);
        return n;
    }

    public static int[] getPrimeMas(int n) {
        int[] resheto = new int[n];
        for (int i = 0; i < n; i++) {
            resheto[i] = i+2;
        }

        for (int i = 0; i < Math.pow(n, 1.0/2)+1; i++) {
            int prime = resheto[i];
            if (prime == -1) continue;
            for (int j = i+1; j < n; j++) {
                if (resheto[j] % prime == 0) {
                    resheto[j] = -1;
                }
            }
        }
        return resheto;
    }
    public static void displayMassive(int[] massive, int quanPrime) {
        System.out.print("Простые числа: ");
        for (int i = 0, displayed = 0; i < massive.length; i++) {
            if (displayed == quanPrime) break;
            if (massive[i] == -1) continue;
            System.out.printf("%d ", massive[i]);
            displayed++;
        }
    }

}
