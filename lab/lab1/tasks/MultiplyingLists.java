//Пусть любое число – это массив его цифр слева направо. Пример, число
//        1234 – это массив [1,2,3,4].
//        Дан массив целых чисел. Реализовать умножение двух чисел.
//        Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//        Результат – число, представленное массивом.

package tasks;
import utils.*;
import java.util.Scanner;

public class MultiplyingLists {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Количество цифр в первом массиве: ");
        int N1 = InputManager.inputTarget(scan);
        int[] number1 = new int[N1];
        ArrayUtils.fillArray(number1, scan);
        System.out.println("Количество цифр во втором массиве: ");
        int N2 = InputManager.inputTarget(scan);
        int[] number2 = new int[N2];
        ArrayUtils.fillArray(number2, scan);
        OutputUtils.printArray(multiplying(number1, number2));
        scan.close();

    }
    private static int[] multiplying(int[] number1, int[] number2){
        return transformationArray(transformationNumber(number1) * transformationNumber(number2));
    }
    private static int transformationNumber(int[] array){
        int sum = 0, digit = 1;
        for (int i = array.length-1; i >= 0 ; i--){
            sum+=array[i]*digit;
            digit*=10;
        }
        return sum;
    }
    private static int[] transformationArray(int number){
        if (number == 0) return new int[]{0};
        int temp = Math.abs(number);
        int length = 0;
        while (temp > 0) {
            temp /= 10;
            length++;
        }
        int[] result = new int[length];
        temp = Math.abs(number);
        for (int i = length - 1; i >= 0; i--) {
            result[i] = temp % 10;  // берем последнюю цифру
            temp /= 10;             // удаляем последнюю цифру
        }
        return result;
    }
}
