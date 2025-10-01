import java.util.Scanner;

public class BinarySearch {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите число элементов массива: ");
        int length = scanner.nextInt();
        int[] array = Arrays.arraySort(Arrays.newArray(length));

        System.out.print("Введите искомый элемент: ");
        int target = scanner.nextInt();

        int target1 = binarySearchIterative(array, target);
        int target2 = binarySearchRecursive(array, target, 0, array.length - 1);

        System.out.println("Номер искомого элемента в списке: ");

        System.out.println("1) Метод итераций: " + (target1 == -1 ? "Номер не найден" : target1));
        System.out.println("2) Метод рекурсии: " + (target2 == -1 ? "Номер не найден" : target2));
    }

    private static int binarySearchIterative(int[] array, int target) {

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            if (array[midIndex] == target) {
                return midIndex;
            } else if (array[midIndex] < target) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] array, int target, int left, int right) {

        int midIndex = left + (right - left) / 2;

        if (left > right) {
            return -1;
        } else if (array[midIndex] == target) {
            return midIndex;
        } else if (array[midIndex] < target) {
            binarySearchRecursive(array, target, midIndex + 1, right);
        } else {
            binarySearchRecursive(array, target, left, midIndex - 1);
        }
        return -1;
    }
}


