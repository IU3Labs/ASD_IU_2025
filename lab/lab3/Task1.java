//Сложность O(n^2), так как используется вложенный цикл
import java.util.Scanner;

public class Task1 {
    private static int length;

    // Создание массива
    private static int[] CreateMassive() {
        System.out.print("Введите размер массива: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Введите число: ");
            data[i] = sc.nextInt();
        }
        length = N;
        return data;
    }

    // Подсчет инверсий в массиве
    private static int CountInv(int[] data) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if (data[i] > data[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = CreateMassive();
        System.out.printf("Количество инверсий в массиве: %d\n", CountInv(arr));

    }
}

