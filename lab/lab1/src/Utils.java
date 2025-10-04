import java.util.Scanner;

public class Utils {
    public static int[] createArray(Scanner scanner) {
        System.out.println("Enter array size:");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter the array elements, one per line:");
        for (int i = 0; i < size; i++) {
            int val = scanner.nextInt();
            array[i] = val;
        }

        return array;
    }

    public static void printArr(int[] array) {
        String line = "";
        for (int i : array) {
            line += String.format("%d, ", i);
        }
        System.out.printf("[%s]\n", line.replaceAll(",\\s*$", ""));
    }

    public static int[] intToArr(int num) {
        String numLine = String.valueOf(Math.abs(num));

        int[] array = new int[numLine.length()];

        for (int i = 0; i < numLine.length(); i++) {
            array[i] = Character.getNumericValue(numLine.charAt(i));
        }
        if (num < 0) {
            array[0] *= -1;
        }
        return array;
    }

    public static int arrToInt(int[] array) {
        String line = "";
        for (int i : array) {
            line += String.valueOf(i);
        }
        return Integer.parseInt(line);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);

            quickSort(array, low, pivot);
            quickSort(array, pivot + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = array[mid];
        int i = low;
        int j = high;

        while (true) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i >= j) {
                return j;
            }

            swap(array, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
