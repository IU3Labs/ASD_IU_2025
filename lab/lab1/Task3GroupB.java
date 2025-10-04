import java.util.Scanner;

public class Task3GroupB {
    public static void main(String[] args) {
        int array [] = inputArray();
        int result = sumOfTwoSmallestInArray(array);
        if (result == -1){
            System.out.println("Массив должен содержать минимум 5 элементов");
        } else if (result == -2){
            System.out.println("В массиве должно быть минимум 2 положительных числа");
        } else {
            System.out.println("Искомая сумма: " + result);
        }
    }

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int length = scanner.nextInt();
        int[] array = new int[length];
        System.out.println("Введите " + length + " элементов массива:");
        for (int i = 0; i < length; i++) {
            System.out.print("Элемент [" + i + "]: ");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int sumOfTwoSmallestInArray(int[] array) {
        if (array == null || array.length < 5) {
            return -1;
        }
        int smallestElement = Integer.MAX_VALUE;
        int secondSmallestElement = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            if (currentElement > 0) {
                if (currentElement < smallestElement) {
                    secondSmallestElement = smallestElement;
                    smallestElement = currentElement;
                } else if (currentElement < secondSmallestElement && currentElement != smallestElement) {
                    secondSmallestElement = currentElement;
                }
            }
        }
        if (smallestElement == Integer.MAX_VALUE || secondSmallestElement == Integer.MAX_VALUE) {
            return -2;
        }
        return smallestElement + secondSmallestElement;
    }
}

/*
3.Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел.
*/
