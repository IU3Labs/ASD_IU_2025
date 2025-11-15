import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EratosthenesSieve {

    // Метод для ввода количества простых чисел
    public static int inputCount() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите количество простых чисел (N > 0): ");
        return Integer.parseInt(reader.readLine());
    }

    // Метод Решето Эратосфена
    public static void sieveOfEratosthenes(int nCount) {
        // Простая оценка верхнего предела
        int limit = nCount * 20;
        if (limit < 100) {
            limit = 100;
        }

        boolean[] prime = new boolean[limit + 1];

        // Инициализация массива
        for (int i = 2; i <= limit; i++) {
            prime[i] = true;
        }

        // Алгоритм Решето Эратосфена
        for (int p = 2; p * p <= limit; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= limit; i += p) {
                    prime[i] = false;
                }
            }
        }

        // Вывод результатов
        System.out.println("\nПервые " + nCount + " простых чисел:");
        int count = 0;
        for (int i = 2; i <= limit && count < nCount; i++) {
            if (prime[i]) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            int n = inputCount();
            if (n > 0) {
                sieveOfEratosthenes(n);
            } else {
                System.out.println("Число должно быть больше 0");
            }
        } catch (Exception e) {
            System.out.println("Ошибка! Введите целое число.");
        }
    }
}
//группа А (1 балла)
//Дано целое число. Реализуйте метод, который находит N первых простых
//чисел. Используйте алгоритм «Решето Эратосфена».