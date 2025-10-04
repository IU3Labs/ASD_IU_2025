package lab1;//Дано целое число. Реализуйте метод, который находит N первых простых
//чисел. Используйте алгоритм «Решето Эратосфена».

import java.util.Scanner;

public class Num4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Введи n: ");
        int n = s.nextInt();
        find(n);
        s.close();
    }

    public static void find(int n) {
        if (n <= 0) {
            System.out.println("n должно быть положительным числом");
            return;
        }
        int max = calculateLimit(n);
        boolean[] primes = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            primes[i] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= max; j += i) {
                    primes[j] = false;
                }
            }
        }
        System.out.println("Первые " + n + " простых чисел:");
        int count = 0;
        for (int i = 2; i <= max && count < n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println();
    }

    public static int calculateLimit(int n) {
        // p(n) ~ n * ln(n) + n * ln(ln(n)) для n >= 6
        if (n <= 0) return 100;
        if (n == 1) return 2;
        if (n == 2) return 3;
        if (n == 3) return 5;
        if (n == 4) return 7;
        if (n == 5) return 11;
        return (int)(n * (Math.log(n) + Math.log(Math.log(n)))) + 100;
    }
}