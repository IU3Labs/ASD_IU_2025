import java.util.Scanner;

public class GroupB_Task1 {
    public static void main(String[] args) {
        int[] massive = getMassive();
        int[] countMassive = crCountMas(massive);
        showNumbers(massive, countMassive);
    }

    public static int[] getMassive() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество чисел в массиве: ");
        int len = sc.nextInt();
        int[] massive = new int[len];
        for (int i = 0; i < len; i++) {
            System.out.print("Введите число: ");
            massive[i] = sc.nextInt();
        }
        return massive;
    }

    public static int[] crCountMas(int[] massive) {
        int[] countMassive = new int[massive.length];
        for (int i = 0; i < massive.length; i++) {
            int count = 0;
            for (int j = i; j < massive.length; j++) {
                if (massive[i] == massive[j]) {
                    count++;
                }
            }
            countMassive[i] = count;
        }
        return countMassive;
    }

    public static void showNumbers(int[] massive, int[] countMassive) {
        for (int i = 0; i < massive.length; i++) {
            if (massive[i] == countMassive[i]) {
                System.out.printf("%d ", massive[i]);
            }
        }
    }
}
