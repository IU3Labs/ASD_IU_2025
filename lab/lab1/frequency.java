//1. Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

import java.util.Scanner;

public class frequency {
    public static int num(int[] array, int n){
        int max = 0;
        for(int i = 0; i < n; i++){
            int c = 0;
            for(int j = 0; j < n; j++){
                if(array[j] == array[i]){
                    c++;
                }
            }
            if (c == array[i] & max < c){
                max = array[i];
            }
        }
        return max;
    }

    public static void arrayFill(int[] array, int n){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");
        for(int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
    }
    public static void main(){
        System.out.print("Введите количество элементов массива: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        arrayFill(array, n);
        int result = num(array, n);
        System.out.println("Искомый элемент: " + result);
    }
}
