import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static int inversions = 0;

    public static void mergeSort(int[] array) {
        if (array.length <= 1) return;

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        for (int i = 0; i < mid; i++) left[i] = array[i];
        for (int i = mid; i < array.length; i++) right[i - mid] = array[i];

        mergeSort(left);
        mergeSort(right);

        int[] merged = merge(left, right);

        for (int i = 0; i < merged.length; i++) {
            array[i] = merged[i];
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int l = 0, r = 0, i = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                result[i++] = left[l++];
            } else {
                // если значение из правого списка меньше значения из левого, то все что после левого значения также формирует инверсию
                inversions += (left.length - l);
                result[i++] = right[r++];
            }
        }

        while (l < left.length) {
            result[i++] = left[l++];
        }

        while (r < right.length) {
            result[i++] = right[r++];
        }

        return result;
    }

    public static void zeroInvers() {
        inversions = 0;
    }

    public static ArrayList<Integer> createArray(Scanner scanner) {
        System.out.println("Enter array size:");
        int size = scanner.nextInt();

        ArrayList<Integer> array = new ArrayList<>();

        System.out.println("Enter the array elements, one per line:");
        for (int i = 0; i < size; i++) {
            int val = scanner.nextInt();
            array.add(val);
        }

        return array;
    }

    public static Integer getLimit(Scanner scanner) {
        System.out.println("Enter number of values to return");
        int k = scanner.nextInt();
        return k;
    }

    public static void printList(ArrayList<Integer> list) {
        System.out.println(list);
    }
}