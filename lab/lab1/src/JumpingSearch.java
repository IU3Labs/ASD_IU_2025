import java.util.Scanner;

public class JumpingSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Utils.readArray(scanner);
        int target = takeTarget(scanner);

        arr = Utils.quickSort(arr, 0, arr.length - 1);
        int index = Utils.jumpingSearch(arr, target);
        showResults(arr, index);
    }


    private static int takeTarget(Scanner scanner) {
        System.out.println("Введите искомое число");
        int target = scanner.nextInt();
        return target;
    }


    private static void showResults(int[] arr, int index) {
        if (index > -1) {
            System.out.println("Искомое число " + arr[index] + " найдено! Его индекс: " + index);
        } else {
            System.out.println("Число не найдено!");
        }
    }
}
