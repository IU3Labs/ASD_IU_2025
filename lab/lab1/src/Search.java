// Группа Б. Номер 1
// Дан целочисленный массив. Верните число, частота встречи которого в
// массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
// таких чисел несколько, вернуть наибольшее.

import java.util.Scanner;

public class Search {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        int[] array = Utils.createArray(scanner);
        Utils.quickSort(array, 0, array.length);

        int maxInt = findSuitable(array);

        System.out.printf("Max suitable number: %d", maxInt);
    }

    private static int findSuitable(int[] array) {
        int maxInt = -1;
        for (int i : array) {
            if (maxInt != i) {
                int total = 0;
                for (int j : array) {
                    if (i == j) {
                        total++;
                    }
                }
                if (total == Math.abs(i) && maxInt < total) {
                    maxInt = i;
                }
            }
        }
        return maxInt;
    }
}
