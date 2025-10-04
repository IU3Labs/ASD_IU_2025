//2 Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.

import java.util.Scanner;


public class multiplying {
    public static void arrayFill(int[] array, int n){
        System.out.print("Введите элементы массива: ");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++){
            array[i] = scanner.nextInt();
        }
    }

    public static int[] numtoarray(int number){
        if (number == 0) return new int[]{0};

        int length = 0;
        int temp = number;
        while(temp > 0){
            temp /= 10;
            length++;
        }
        int[] result = new int[length];
        temp = number;
        for(int i = length - 1; i >= 0; i--){
            result[i] = temp % 10;
            temp /= 10;
        }
        return result;
    }

    public static int arraytonum(int[] array){
        int number = 0;
        for(int i = 0; i < array.length; i++){
            number = number * 10 + array[i];
        }
        return number;
    }

    public static int[] multiplyarrays(int[] arr1, int[] arr2){
        int num1 = arraytonum(arr1);
        int num2 = arraytonum(arr2);
        int result = num1 * num2;
        return numtoarray(result);
    }

    public static void main(String[] args){
        int n = 2;
        int[] array = new int[n];
        arrayFill(array, n);
        int[] arr1 = numtoarray(array[0]);
        int[] arr2 = numtoarray(array[1]);
        System.out.println("Массивы, которые нужно перемножить: ");
        System.out.print("[ ");
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + " ");
        }
        System.out.print("] * [ ");
        for(int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println("]");
        int[] result = multiplyarrays(arr1, arr2);
        System.out.print("Перемноженные массивы: ");
        System.out.print("[ ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("]");
    }
}

