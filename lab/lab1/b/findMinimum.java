package lab1.b;
/* 1.Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел. */

import java.util.Scanner;

public class findMinimum {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Введите длину массива");
        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        System.out.print("Введите элементы массива");
        inputArray(array);
        System.out.print("Минимальное число: " + minNumber(array));
    }
    static void inputArray(int[] array){
        for(int i=0;i<array.length;i++){
            array[i]=input.nextInt();
        }
    }
    static int minNumber(int[] array){
        int firstMinimum = -1;
        int secondMinimum = -1;
        for (int j : array) {
            if (j > 0) {
                if (firstMinimum == -1) {
                    firstMinimum = j;
                } else if (secondMinimum == -1) {
                    if (j > firstMinimum) {
                        secondMinimum = j;
                    } else {
                        secondMinimum = firstMinimum;
                        firstMinimum = j;
                    }
                } else {
                    if (j < firstMinimum) {
                        secondMinimum = firstMinimum;
                        firstMinimum = j;
                    } else if (j < secondMinimum) {
                        secondMinimum = j;
                    }
                }
            }
        }
        if (firstMinimum != -1 && secondMinimum != -1) {
            return firstMinimum + secondMinimum;
        } else return 0;
    }


}
