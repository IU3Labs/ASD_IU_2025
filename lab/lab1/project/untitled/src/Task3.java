/*
3 Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел.
 */
import java.util.Scanner;
public class Task3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = ArrayUtils.inputArray(sc);
        System.out.println("массив:");
        ArrayUtils.bubbleSort(arr);
        ArrayUtils.printArray(arr);
        int result = elInArr(arr);
        if (result == -1) {
            System.out.println("В массиве меньше двух положительных чисел");
        } else {
            System.out.println("Сумма двух наименьших положительных чисел: " + result);
        }
    }
    private static int elInArr(int [] arr){
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int i =0;i<arr.length;i++){
            if(arr[i]<min1 && arr[i]> 0){
                min1 = arr[i];
            }
        }
        for(int i = 0;i < arr.length;i++){
            if(arr[i]>0 && arr[i]!= min1 && arr[i]<min2){
                min2 = arr[i];
            }
        }
        if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
            return -1;
        }
        return min1 + min2;
    }
}