import java.util.Scanner;

public class FrequencyEqualsValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] numbers = new int[size];
        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        int result = ArrayProcessor.findLuckyNumber(numbers);
        System.out.println("Result: " + result);

        scanner.close();
    }
}