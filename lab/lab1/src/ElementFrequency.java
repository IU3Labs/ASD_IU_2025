//Группа Б.
//Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

public class ElementFrequency {
    public static void main(String[] args) {
        int[] array = ArrayMethods.newArray();
        int result = findNumberWithFrequencyEqualToValue(array);
        System.out.println(result);
    }

    public static int findNumberWithFrequencyEqualToValue(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int maxValue = findMaxValue(array);
        int[] frequency = calculateFrequency(array, maxValue);
        return findResult(frequency);
    }

    private static int findMaxValue(int[] array) {
        int maxValue = array[0];
        for (int i : array) {
            if (i > maxValue) {
                maxValue = i;
            }
        }
        return maxValue;
    }

    private static int[] calculateFrequency(int[] array, int maxValue) {
        int[] frequency = new int[maxValue + 1];
        for (int i : array) {
            if (i >= 0 && i <= maxValue) {
                frequency[i]++;
            }
        }
        return frequency;
    }

    private static int findResult(int[] frequency) {
        int result = -1;
        for (int i = 1; i < frequency.length; i++) {
            if (i == frequency[i] && i > result) {
                result = i;
            }
        }
        return result;
    }
}