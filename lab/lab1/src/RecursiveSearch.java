import java.util.Scanner;

public class RecursiveSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Utils.readArray(scanner);
        arr = Utils.quickSort(arr, 0, arr.length - 1);
        int target = takeTarget(scanner);
        int target_index = recursionSearch(arr, target, 0, arr.length - 1);
        showResults(target, target_index);
    }

    private static int recursionSearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return recursionSearch(arr, target, mid + 1, right);
        }
        return recursionSearch(arr, target, left, mid - 1);
    }

    private static int takeTarget(Scanner scanner) {
        System.out.println("Введите искомую величину:");
        return scanner.nextInt();
    }

    private static void showResults(int target, int target_index) {
        if (target_index == -1) {
            System.out.println("Число " + target + " не найдено:(");
        } else {
            System.out.println("Число " + target + " найдено! Его индекс: " + target_index);
        }
    }
}