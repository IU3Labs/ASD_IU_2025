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
        int startIndex = 0;
        int endIndex = array.length - 1;

        while (startIndex <= endIndex){
            int middleIndex = startIndex + (endIndex - startIndex) / 2;
            if (array[middleIndex] == target){
                System.out.println("Индекс: " + middleIndex);
                return;
            }
            else if (array[middleIndex] < target){
                startIndex = middleIndex + 1;
            }
            else{
                endIndex = middleIndex - 1;
            }
        }
        System.out.println("Элемент не найден");
    }

    public static void binarySearchRecursive(int[] array, int target){
        binarySearchRecursive(array, target, 0, array.length - 1);
    }

    public static void binarySearchRecursive(int[] array, int target, int startIndex, int endIndex){
        if (startIndex > endIndex){
            System.out.println("Элемент не найден");
            return;
        }
        int middleIndex = startIndex + (endIndex - startIndex) / 2;

        if (array[middleIndex] == target){
            System.out.println("Индекс: " + middleIndex);
        }
        else if (array[middleIndex] < target){
            binarySearchRecursive(array, target, middleIndex + 1, endIndex);
        }
        else{
            binarySearchRecursive(array, target, startIndex, middleIndex - 1);
        }
    }

}