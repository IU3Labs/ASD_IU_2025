import java.util.*;

public class Task4 {
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

    private static boolean Comparer(int a, int b, int mask) {
        if ((a & mask) < (b & mask)) {
            return true;
        } else if ((a & mask) > (b & mask)) {
            return false;
        } else {
            return (a < b);
        }
    }

    private static ArrayList<Integer> Merge(ArrayList<Integer> leftArr, ArrayList<Integer>rightArr, int mask) {
        ArrayList<Integer> merged = new ArrayList<>(leftArr.size()+rightArr.size());
        while (!leftArr.isEmpty() && !rightArr.isEmpty()) {
            if (Comparer(leftArr.getFirst(), rightArr.getFirst(), mask)) {
                merged.add(leftArr.getFirst());
                leftArr.removeFirst();
            } else {
                merged.add(rightArr.getFirst());
                rightArr.removeFirst();
            }
        }
        merged.addAll(leftArr);
        merged.addAll(rightArr);
        return merged;
    }

    private static ArrayList<Integer> MergeSort(ArrayList<Integer> arr, int mask) {
        if (arr.size() == 1) {
            return arr;
        }
        int half = arr.size() / 2;
        ArrayList<Integer> leftArr = new ArrayList<Integer>(half);
        ArrayList<Integer> rightArr = new ArrayList<Integer>(arr.size()-half);
        for (int i = 0; i < half; i++) {
            leftArr.add(arr.get(i));
        }
        for (int j = half; j < arr.size(); j++) {
            rightArr.add(arr.get(j));
        }
        leftArr = MergeSort(leftArr, mask);
        rightArr = MergeSort(rightArr, mask);

        return Merge(leftArr, rightArr, mask);
    }

    public static void main(String[] args) {
        // 1. Создание массива
        ArrayList<Integer> arr = createMassive();
        System.out.println("Исходный массив: " + arr);
        Scanner sc = new Scanner(System.in);
        int mask;
        System.out.print("Введите битовую маску в виде десятичного целого числа: ");
        mask = sc.nextInt();
        arr = MergeSort(arr, mask);
        System.out.println("Массив отсортирован с применением маски: " + arr);

    }
}