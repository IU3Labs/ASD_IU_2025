/*
1.  Дан целочисленный массив.
Верните число, частота встречи которого в массиве равна его значению.
Если таких чисел нет, вернуть «-1».
Если таких чисел несколько, вернуть наибольшее.
 */
package lab1.groupb.frequency;

import lab1.utils.ArrayUtils;

import java.util.Scanner;

public class Frequency {
    public static void main() {
        Scanner in = new Scanner(System.in);
        int lenArray = ArrayUtils.getLenArray(in);
        int[] array = new int[lenArray];
        ArrayUtils.getIntArray(in, array, lenArray);

        System.out.println("Find number: " + findFreqNumber(array));
    }

    public static int findFreqNumber(int[] array) {
        int maxFindNumber = -1;
        boolean isFindNumber = false;
        for (int key : array) {
            if (countElements(array, key) == key) {
                if (!isFindNumber) {
                    isFindNumber = true;
                    maxFindNumber = key;
                } else {
                    maxFindNumber = Math.max(key, maxFindNumber);
                }

            }
        }
        if (!isFindNumber) {
            return -1;
        }
        return maxFindNumber;
    }

    public static int countElements(int[] array, int key) {
        int count = 0;
        for (int elem : array) {
            if (elem == key) {
                count++;
            }
        }
        return count;
    }
}
