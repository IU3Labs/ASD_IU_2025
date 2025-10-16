package lab1;

import java.util.Scanner;

public class ThirdDop extends Zero {
        private static final Scanner in = new Scanner(System.in);
        public static void main (String[] args) {
            thirdDopTask();
            in.close();
        }
    public static void thirdDopTask () {

        // ЗАДАНИЕ:
        // Дан массив целых чисел и целое число. Реализовать метод, который
        // возвращает индексы тех двух чисел массива, которые дают сумму
        // заданного числа. Индексы вернуть в любом порядке. Один элемент в сумме
        // использовать дважды нельзя.
        // Примечание. Задача должна быть решена со сложностью меньше, чем
        // ( !). В комментариях кода привести доказательство, что сложность
        // меньше.

        System.out.println(" --------------- Third task ---------------");
        byte[] massive3 = inData(); // ввод
        int n = inNumber(); // ввод
        for (int i = 0; i < massive3.length; i++ ) {
            for (int j = 0; (j < massive3.length && j != i); j++ ) { // сложность меньше, тк во 2-м цикле мы пробегаемся по n - 1 элементам
                if (massive3[i] + massive3[j] == n) {
                    System.out.println("Индексы: " + i + ", " + j);
                }
            }
        }
        System.out.println("-1");

    }
}
