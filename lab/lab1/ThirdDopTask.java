package lab1;

import java.util.Scanner;

public class ThirdDopTask extends BaseTask { // тк множественное не наследуется, но нужны и методы FunctionsLab1, и бинарный поиск
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        thirdDopTask();
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
        int diff = -1;
        int[] ans = new int[2];
        for (int i = 0; i < massive3A.length; i++) { // Первый цикл, сложность алгоритма = 0+n
            if (massive3A[i] < n)
            {
                massive3B[massive3A[i]] = i;
            }
        }
        print(massive3B);

        for (int j = 0; j < massive3B.length / 2 + 1; j++) { // Второй цикл, сложность алгоритма = n+ n/2 => линейна
            if (massive3B[j] != 0 & massive3B[n - j] != 0) {
                ans[0] = massive3B[j];
                ans[1] = massive3B[n - j];
                System.out.println(ans[0] + " " + ans[1]);
                return ans;
            }
        }
        return ans;
    }
}