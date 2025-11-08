/*
3.  Дан массив целых чисел.
Минимальное количество элементов – 5.
Вернуть число, которое является суммой двух наименьших положительных чисел.
*/
package lab1.groupb.sumpositive;

import lab1.utils.ArrayUtils;

import java.util.Scanner;

public class SumPositive {
    public static void main() {
        Scanner in = new Scanner(System.in);
        int lenArray = -1;
        while (lenArray < 5) {
            System.out.print("Input length array (>4): ");
            lenArray = in.nextInt();
        }
        int[] array = new int[lenArray];
        ArrayUtils.getIntArray(in, array, lenArray);
        
        int[] answer = new int[2];
        findTwoMinPositive(array,answer);
        printAnswer(answer);

    }

    public static void findTwoMinPositive(int[] array, int[] answer) {
        if (answer.length != 2) {
            return;
        }
        answer[0]=-1;
        answer[1]=-1;
        for (int elem : array) {
            if (elem > 0) {
                if (answer[0] == -1) {
                    answer[0] = elem;
                } else {
                    if (answer[0] > elem) {
                        answer[1] = answer[0];
                        answer[0] = elem;
                    } else {
                        if (answer[1] == -1) {
                            answer[1] = elem;
                        } else if (elem < answer[1]) {
                            answer[1] = elem;
                        }
                    }
                }
            }
        }
    }

    public static void printAnswer(int[] answer) {
        if (answer.length != 2) {
            return;
        }
        if (answer[0] == -1 || answer[1] == -1) {
            System.out.println("In array not 2 positive numbers");
        } else {
            System.out.println("Sum min positive numbers: " + (answer[0] + answer[1]));
        }
    }
}
