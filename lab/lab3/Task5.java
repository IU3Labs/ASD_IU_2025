import java.util.*;

public class Task5 {
    // Создание массива
    private static ArrayList<Integer> createMassive() {
        System.out.print("Введите размер массива: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> data = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            System.out.print("Введите число: ");
            data.add(sc.nextInt());
        }
        return data;
    }

    public static void main(String[] args) {
        // 1. Создание массива
        ArrayList<Integer> arr = createMassive();
        System.out.println("Исходный массив: " + arr);
        for (int i = 0; i < (arr.size() - 1); i++) {
            if (arr.get(i) > arr.get(i+1)) {
                System.out.println("Минимальный элемент массива: " + arr.get(i+1));
            }
        }


    }
}