package lab1.b;

import java.util.Scanner;
import lab1.FillArray;

public class Frequency {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов массива: ");
        int count = scanner.nextInt();
        int[] array = new int[count];

        FillArray.fillArray(array);

        int result = findNumberWithFrequencyEqualToValue(array);
        System.out.println("Искомый элемент: " + result);

        scanner.close();
    }
    public static int findNumberWithFrequencyEqualToValue(int[] numbers) {
        int maxNumber = -1;

        for (int i = 0; i < numbers.length; i++) {
            int frequency = countFrequency(numbers, numbers[i]);

            if (frequency == numbers[i] && numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
        }

        return maxNumber;
    }

    private static int countFrequency(int[] numbers, int targetNumber) {
        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == targetNumber) {
                count++;
            }
        }

        return count;
    }
}