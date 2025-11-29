public class ArrayProcessor {

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

    public static int calculateSumTwoSmallestPositive(int[] array) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int value : array) {
            if (value > 0) {
                if (value < min1) {
                    min2 = min1;
                    min1 = value;
                } else if (value < min2) {
                    min2 = value;
                }
            }
        }

        if (min2 == Integer.MAX_VALUE) {
            return -1;
        }

        return min1 + min2;
    }

    public static boolean isLuckyNumber(int number, int frequency) {
        return number == frequency;
    }

    public static int countFrequency(int[] numbers, int target) {
        int count = 0;
        for (int number : numbers) {
            if (number == target) {
                count++;
            }
        }
        return count;
    }
}