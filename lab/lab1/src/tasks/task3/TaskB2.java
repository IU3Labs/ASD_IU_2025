//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.
package tasks.task3;

import java.util.Arrays;
import java.util.Scanner;

public class TaskB2 {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] createArray(int number){

        int absNumber = Math.abs(number);
        int count = 0;

        do {
            count++;
            absNumber /= 10;
        } while(absNumber > 0);

        int[] digitsArray = new int[count];

        absNumber = Math.abs(number);
        for(int i = count - 1; i >= 0; i--){
            digitsArray[i] = absNumber % 10;
            absNumber /= 10;
        }

        return digitsArray;
    }

    public static int[] multiplyArray(int number1, int number2) {
        if (number1 == 0 || number2 == 0){
            return new int[]{0};
        }

        int sign = (number1 < 0) ^ (number2 < 0) ? -1 : 1;

        int[] arrayNumber1 = createArray(number1);
        int[] arrayNumber2 = createArray(number2);

        int lenArrayNumber1 = arrayNumber1.length;
        int lenArrayNumber2 = arrayNumber2.length;
        int[] resultArray = new int[lenArrayNumber2 + lenArrayNumber1];;
        for(int i = lenArrayNumber1 - 1; i >= 0; i--){
            for(int  j = lenArrayNumber2 - 1; j >= 0; j--){
                int multiplyNumber = arrayNumber1[i]*arrayNumber2[j];
                int tensInd = i + j;
                int unitsInd = i + j + 1;
                int summaryNumber = multiplyNumber + resultArray[unitsInd];

                resultArray[unitsInd] = summaryNumber%10;
                resultArray[tensInd] += summaryNumber/10;
            }
        }

        resultArray[0] = resultArray[0] * sign;

        if (resultArray[0] == 0) {
            int[] shotrenedArray = new int[resultArray.length - 1];
            for (int i = 0; i < shotrenedArray.length; i++){
                shotrenedArray[i] = resultArray[i+1];
            }
            shotrenedArray[0] = shotrenedArray[0] * sign;
            return shotrenedArray;
        }


        return resultArray;
    }

    public static void taskB2() {
        System.out.print("Добро пожаловать, мой господин!\nВведите первое число: ");
        int number1 = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int number2 = scanner.nextInt();

        System.out.println(Arrays.toString(multiplyArray(number1, number2)));

    }
}
