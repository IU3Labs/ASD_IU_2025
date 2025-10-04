/*
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */
import java.util.Scanner;
public class Task4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = ArrayUtils.inputArray(sc);
        ArrayUtils.bubbleSort(arr);
        System.out.println("массив:");
        ArrayUtils.printArray(arr);
        int res = findNumber(arr);
        System.out.println("результат:"+ res);
    }
    private static int findNumber(int[] arr) {
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    count++;
                }
            }
            if (count == arr[i] && arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

