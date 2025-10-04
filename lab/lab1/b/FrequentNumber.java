package lab1.b;

/*Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее. */

import java.util.Scanner;
import lab1.Arrays;

public class FrequentNumber {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите длину массива");
        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        System.out.println("Введите элементы массива");
        Arrays.inputArray(array);
        System.out.println("Частый элемент" + findFrequentNumber(array));
    }
    static int findFrequentNumber(int [] array){
        int temp =-1;
        int tempMax =0;
        for ( int j : array){
            int freq = count(array, j);
                    if (freq > tempMax){
                        temp = j;
                        tempMax = freq;

                    }
        }
        return temp;
    }
    static int count(int [] array,int number){
        int count = 0;
        for (int j :  array) {
            if (j == number){
                count++;
            }

        }
        return count;
    }


}
