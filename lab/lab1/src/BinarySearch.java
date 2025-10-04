//Основное задание. Реализовать алгоритм бинарного поиска двумя способами.

import java.util.Scanner;

public class BinarySearch {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int[] array = ArrayMethods.newArray();

        System.out.println("Какой элемент нужно найти?");
        int target = scanner.nextInt();
        binarySearchIterative(array, target);
        binarySearchRecursive(array, target);
    }

    public static void binarySearchIterative(int[] array, int target){
        int left = 0;
        int right = array.length - 1;
        int index;

        while (left <= right){
            index = left + (right - left) / 2;
            if (array[index] == target){
                System.out.println("Индекс: " + index);
                return;
            }
            else if (array[index] < target){
                left = index + 1;
            }
            else{
                right = index - 1;
            }
        }
        System.out.println("Элемент не найден");
    }

    public static void binarySearchRecursive(int[] array, int target){
        binarySearchRecursive(array, target, 0, array.length - 1);
    }

    public static void binarySearchRecursive(int[] array, int target, int left, int right){
        if (left > right){
            System.out.println("Элемент не найден");
            return;
        }
        int index = left + (right - left) / 2;

        if (array[index] == target){
            System.out.println("Индекс: " + index);
        }
        else if (array[index] < target){
            binarySearchRecursive(array, target, index + 1, right);
        }
        else{
            binarySearchRecursive(array, target, left, index - 1);
        }
    }

}