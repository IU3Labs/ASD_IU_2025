import java.util.Scanner;

public class JumpingSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Utils.readArray(scanner);
        int target = takeTarget(scanner);

        int[] sorted_arr = Utils.quickSort(arr, 0, arr.length - 1);
        int target_index = Utils.jumpingSearch(sorted_arr, target);
        showResults(arr, target_index);
    }

    private static int takeTarget(Scanner scanner) {
        System.out.println("Введите искомое число");
        int target = scanner.nextInt();
        return target;
    }

    private static void showResults(int[] arr, int target_index) {
        if (target_index > -1) {
            System.out.println("Искомое число " + arr[target_index] + " найдено! Его индекс: " + target_index);
        } else {
            System.out.println("Число не найдено!");
        }
    }


}
