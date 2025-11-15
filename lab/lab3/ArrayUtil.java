import java.util.Scanner;

public class ArrayUtil {
    public static final Scanner scanner = new Scanner(System.in);

    public static int[] inputArray() {
        System.out.println("Длина массива:");
        int length = scanner.nextInt();
        int[] nums = new int[length];

        System.out.println("Введите массив:");
        for (int i = 0; i < length; i++) {
            nums[i] = scanner.nextInt();
        }
        return nums;
    }
}
