package lab1;

//Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
//число, которое является суммой двух наименьших положительных чисел.

import java.util.Scanner;

public class ThirdTaskGroupB {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов в массиве: ");
        int countElements = scanner.nextInt();
        check(InputArray(countElements));
    }

    public static int[] InputArray(int countElements) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");
        int[] array = new int[countElements];
        for (int i = 0; i < countElements; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }


    public static String check(int[] arr) {
        if (arr.length < 5 || arr == null) {
            System.out.println("Ошибка: массив должен содержать минимум 5 элементов");
            return "Ошибка: массив должен содержать минимум 5 элементов";
        }

        int positiveNum[] = new int[arr.length];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                positiveNum[count] = arr[i];
                count++;
            }
        }

        if (count < 2) {
            System.out.println("Ошибка: массив должен содержать минимум 2 положительных числа");
            return "Ошибка: массив должен содержать минимум 2 положительных числа";
        }

        int[] resultArr = new int[count];  //Здесь мы создаем массив только из положительных чисел
        for (int i = 0; i < count; i++) {
            resultArr[i] = positiveNum[i];
        }

        insertionSort(resultArr);

        int sum = resultArr[0] + resultArr[1];
        System.out.println("Сумма 2-х положительных чисел:" + sum);
        return "Сумма 2-х положительных чисел: " + sum;
    }


    public static void insertionSort(int[] arr) {            //Сортировка вставками
        for (int left = 0; left < arr.length; left++) {
            int value = arr[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = value;
        }
    }
}

