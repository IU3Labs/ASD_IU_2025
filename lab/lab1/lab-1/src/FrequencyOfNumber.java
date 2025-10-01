import java.util.Scanner;

public class FrequencyOfNumber {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = -1;

        System.out.print("Введите число элементов массива: ");
        int length = scanner.nextInt();
        int[] array = Arrays.arraySort(Arrays.newArray(length));

        for (int i : array) {
            if ((counterOfNumber(array, i) == i) && i > result) {
                result = i;
            }
        }
        System.out.println("Искомое число: " + result);
    }

    private static int counterOfNumber(int[] array, int number) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                counter++;
            }
        }
        return counter;
    }
}
