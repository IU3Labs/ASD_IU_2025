package lab.lab3.mostcommonelement;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Читаем n
        int n = in.nextInt();

        // Читаем массив
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        // Читаем k
        int k = in.nextInt();

        // Вызываем твой алгоритм
        MostCommonElement s = new MostCommonElement();
        int[] result = s.topKFrequent(nums, k);

        // Выводим результат
        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}
