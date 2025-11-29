import java.util.Scanner;

public class SumTwoSmallestPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        if (size < 5) {
            System.out.println("Error: array must contain at least 5 elements");
            scanner.close();
            return;
        }

        int[] array = new int[size];
        System.out.println("Enter " + size + " elements:");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int result = ArrayProcessor.calculateSumTwoSmallestPositive(array);
        System.out.println("Result: " + result);

        scanner.close();
    }
}