//1 Дан целочисленный массив. Верните число, частота встречи которого в
//        массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//        таких чисел несколько, вернуть наибольшее.

package tasks;
import utils.*;
import java.util.Scanner;

public class ArrayFrequencies {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество элементов: ");
        int n = InputManager.inputTarget(scan);
        int[] array = new int[n];
        ArrayUtils.fillArray(array, scan);
        System.out.println(searchtarget(array));
        scan.close();
    }

    private static int searchtarget(int[] array){
        if (array.length == 0) return -1;

        int min = searchextr(array)[0];
        int max = searchextr(array)[1];
        int[] freqArray = creatingOptimizedArray(min, max);
        countingRepetitions(array, freqArray, min);
        return searchSuitable(freqArray, min);
    }

    private static int[] searchextr(int[] array){
        int min = array[0], max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }
        return new int[]{min, max};
    }

    private static int[] creatingOptimizedArray(int min, int max){
        return new int[max - min + 1];
    }

    private static void countingRepetitions(int[] commonArray, int[] optimizedArray, int min){
        for (int i = 0; i < commonArray.length; i++){
            optimizedArray[commonArray[i] - min]++;
        }
    }

    private static int searchSuitable(int[] optimizedArray, int min){
        int result = -1;
        for (int i = 0; i < optimizedArray.length; i++){
            int number = i + min;
            int frequency = optimizedArray[i];
            if (frequency == number && number > result) {
                result = number;
            }
        }
        return result;
    }
}