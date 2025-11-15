package lab1;

import java.util.Scanner;

import static lab1.FunctionsLab1.*;

public class ThirdDopTask {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int[] answer = thirdDopTask();
        print(answer);
        in.close();
    }

    public static int[] thirdDopTask() {

        // ЗАДАНИЕ:
        // Дан массив целых чисел и целое число. Реализовать метод, который
        // возвращает индексы тех двух чисел массива, которые дают сумму
        // заданного числа. Индексы вернуть в любом порядке. Один элемент в сумме
        // использовать дважды нельзя.
        // Примечание. Задача должна быть решена со сложностью меньше, чем
        // ( !). В комментариях кода привести доказательство, что сложность
        // меньше.

        System.out.println(" --------------- Third task ---------------");
        int[] massive3A = inIntList(); // ввод
        int n = inNumber(); // ввод
        int[] massive3B = new int[n+1];
        for (int i = 0; i < n; i++) {
            massive3B[i] = -1;
        }
        int[] ans = new int[2];
        for (int i = 0; i < massive3A.length; i++) { // Первый цикл, сложность алгоритма = 0+n
            if (massive3A[i] < n) {
                massive3B[massive3A[i]] = i;
            }
        }
        for (int j = 0; j < massive3B.length / 2 + 1; j++) { // Второй цикл, сложность алгоритма = n+ n/2 => линейна
            if (massive3B[j] != -1 & massive3B[n - j] != -1) {
                ans[0] = massive3B[j];
                ans[1] = massive3B[n - j];
                return ans;
            }
        }
        return ans;
    }
}