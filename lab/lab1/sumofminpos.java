//3 Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.

import java.util.Scanner;

public class sumofminpos {
    public static int sumtwo(int[] array) {
        int firstmin = -1;
        int secondmin = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (firstmin == -1) {
                    firstmin = array[i];
                } else if (firstmin > array[i]) {
                    secondmin = firstmin;
                    firstmin = array[i];
                } else if (secondmin == -1 || secondmin > array[i]) {
                    secondmin = array[i];
                }
            }
        }
        if (firstmin == -1 || secondmin == -1) {
            return -1;
        }
        return firstmin + secondmin;
    }

    public static void arrayFill(int[] array, int n){
        System.out.print("Введите элементы массива: ");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < n; i++){
            array[i] = scanner.nextInt();
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        while (true){
            System.out.print("Введите количество элементов массива(минимум 5): ");
            n = scanner.nextInt();
            if(n >= 5){
                break;
            }else {
                System.out.print("Введите число не меньше 5.");
            }
        }
        int[] array = new int[n];
        arrayFill(array, n);
        System.out.println("Исходный массив: ");
        System.out.print("[ ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
        int result = sumtwo(array);
        if(result == -1){
            System.out.print("В массиве недостаточно положительных чисел");
        }else {
            System.out.print("Сумма двух наименьших положительных элементов массива: " + result);
        }
        scanner.close();
    }
}
