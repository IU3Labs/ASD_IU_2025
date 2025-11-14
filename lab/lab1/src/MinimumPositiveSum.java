/*
Группа Б, задание 3
Дан массив целых чисел. Минимальное количество элементов – 5
Вернуть число, которое является суммой двух наименьших положительных чисел.
 */

import java.util.Scanner;

public class MinimumPositiveSum {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[]args){
        System.out.print("Введите количество элементов: ");
        int length = scanner.nextInt();
        while (length<5){
            System.out.println("Необходим массив с 5 и более элементами");
            length = scanner.nextInt();
        }
        int[] array = ArrayTools.createArray(length);
        if(summa(array) == -1){
            System.out.print("Не нашлось двух положительных чисел");
        } else {
            System.out.print("Сумма: " + summa(array));
        }
    }

    private static int summa(int[] array){
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for(int k:array){
            if (k>0) {
                if (k<firstMin) {
                    secondMin = firstMin;
                    firstMin = k;
                } else if (k<secondMin){
                    secondMin=k;
                }
            }
        }
        if (firstMin==Integer.MAX_VALUE || secondMin==Integer.MAX_VALUE){
            return -1;
        }
        return (firstMin+secondMin);
    }
}

