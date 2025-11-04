import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = Additionals.inputArray();

        Additionals.instertionSort(array);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();


        int result = binarySearch(array, target);
        if (result != -1) {
            System.out.println("Элемент найден на индексе: " + result);
        } else {
            System.out.println("Элемент не найден в массиве.");
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}