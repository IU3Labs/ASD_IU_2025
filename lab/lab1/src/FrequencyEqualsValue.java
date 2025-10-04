/*Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.*/

import java.util.Scanner;

public class FrequencyEqualsValue {

    public static int findLuckyNumber(int[] numbers) {
        int maxLuckyNumber = -1;

        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            int frequency = countFrequency(numbers, currentNumber);

            if (isLuckyNumber(currentNumber, frequency) && currentNumber > maxLuckyNumber) {
                maxLuckyNumber = currentNumber;
            }
        }

        return maxLuckyNumber;
    }

    private static int countFrequency(int[] numbers, int target) {
        int count = 0;
        for (int number : numbers) {
            if (number == target) {
                count++;
            }
        }
        return count;
    }

    private static boolean isLuckyNumber(int number, int frequency) {
        return number == frequency;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] numbers = new int[size];
        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        int result = findLuckyNumber(numbers);
        System.out.println("Result: " + result);

        scanner.close();
    }
}