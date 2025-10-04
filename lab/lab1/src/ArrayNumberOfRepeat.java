package src;/*
Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению.
Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
 */
import java.util.Scanner;
public class ArrayNumberOfRepeat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = ArrayUtil.inputArray(scanner);

        System.out.println("Исходный массив:");
        ArrayUtil.printArray(array);

        int answer = findNumber(array);
        System.out.println("Результат: " + answer);
    }
    private static int findNumber( int[] arr){
        int result = -1;
        for (int i = 0; i < arr.length; i++){
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    count++;
                }
            }
            if (arr[i] == count){
                if (arr[i] > result){
                    result = arr[i];
                }
            }
        }
        return result;
    }
}