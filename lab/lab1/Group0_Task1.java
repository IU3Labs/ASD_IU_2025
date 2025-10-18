import java.util.Scanner;

public class Group0_Task1 {
    public static void main(String[] args) {
        int massiveLen = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        massiveLen = sc.nextInt();
        int[] massive = new int[massiveLen];

        massiveFill(massive, sc);
        massiveDisplay(massive);

        System.out.println("Алгоритм бинарного поиска\nВнимание! Массив должен быть отсортирован!\n" +
                "Введите число, которое требуется найти: ");
        int target = sc.nextInt();
        binarySearchV1(massive, target);
        binarySearchV2(massive, target);

    }

    public static void massiveFill(int[] massive, Scanner sc) {
        System.out.println("Заполнение отсортированного массива...");
        for (int i = 0; i < massive.length; i++) {
            System.out.print("Введите число: ");
            massive[i] = sc.nextInt();
        }
    }

    public static void massiveDisplay(int[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.println(massive[i]);
        }
    }

    public static void binarySearchV1(int[] massive, int target) {

        int tLength = massive.length;
        int addon = 0;
        while (true) {
            int elem = massive[(tLength - 1) / 2 + addon];
            if (elem < target) {
                addon += (tLength - 1) / 2 + 1;
                tLength = tLength / 2;
            } else if (elem > target) {
                tLength = tLength / 2;
            } else {
                System.out.print("Элемент найден на позиции ");
                System.out.println((tLength - 1) / 2 + addon);
                break;
            }
            if (tLength == 0) {
                System.out.println("Такого числа в массиве нет");
                break;
            }
        }
    }

    public static void binarySearchV2(int[] massive, int target) {
        int tLength = massive.length;
        int history = 0;
        while (true) {
            int offset = 0;
            int elem = massive[(tLength - 1) / 2];
            if (elem == target) {
                System.out.print("Элемент найден на позиции ");
                System.out.println((tLength - 1) / 2 + history);
                break;
            }
            if (elem < target) {
                offset = (tLength - 1) / 2 + 1;
            }
            tLength = tLength / 2;
            int[] tMassive = new int[tLength];
            for (int i = 0; i < tLength; i++) {
                tMassive[i] = massive[offset + i];
            }
            massive = tMassive;
            history += offset;

            if (tLength == 0) {
                System.out.println("Такого числа в массиве нет");
                break;
            }
        }
    }
}
