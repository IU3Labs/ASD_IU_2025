/*
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */

package lab1;

public class TaskB1 {
    public static void main(String[] args) {
        System.out.println(searchNumber(MainTask.readArray()));
    }

    private static int searchNumber(int[] array) {
        int maxValue = 0;
        for (int i = 0; i < array.length; i++) {
            int count = 1;
            int value = array[i];

            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i] == array[j]){
                    count += 1;
                }
            }

            if (count == value && value > maxValue) {
                maxValue = value;
            }
        }

        if (maxValue == 0) return -1;
        return  maxValue;
    }
}
