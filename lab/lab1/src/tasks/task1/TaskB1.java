//Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.
package tasks.task1;

import java.io.IOException;

import helper.ArrayTools;


public class TaskB1 {
    public static int findNumber(int sizeOfArray, int[] array) {
        int result = -1;
        int count = 1;
        for (int i = 0; i < sizeOfArray; i++) {
            if (array[i] > 0) {
                for (int j = i + 1; j < sizeOfArray; j++) {
                    if (array[i] == array[j]) {
                        count++;
                    }
                }
                if (array[i] == count && array[i] > result) {
                    result = array[i];
                }
                count = 1;
            }
        }
        return result;
    }

    public static void taskB1() throws IOException {
        int[] array = ArrayTools.createIntArray();
        System.out.println(findNumber(array.length, array));
    }
}
