import java.util.Scanner;

public class TaskZero {
    public static void main(String[] args) {
        int[] array = inputArray();
        insertionSort(array);
        System.out.println("Отсортированный массив: ");
        printArray(array);
        System.out.println("Введите элемент: ");
        int number = inputNumber();
        int index = binarySearch(array, number);
        if (index != -1) {
            System.out.println("Элемент " + number + " с индексом " + index + " найден");
        } else {
            System.out.println("Элемент " + number + " не найден");
        }
        recursiveBinarySearch(array, 0,array.length-1, number);
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

    public static void insertionSort(int[] array) {
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }
            array[i + 1] = value;
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        return  scanner.nextInt();
    }

    public static int binarySearch(int[] array, int number){
        int leftBorder = 0;
        int rightBorder = array.length - 1;

        while (leftBorder <= rightBorder) {
            int middleIndex = (leftBorder + rightBorder) / 2;
            if (array[middleIndex] == number){
                return middleIndex;
            } else if(array[middleIndex] < number){
                leftBorder = middleIndex + 1;
            } else {
                rightBorder = middleIndex - 1;
            }
        }
        return -1;
    }


    public static int recursiveBinarySearch(int array[], int leftBorder, int rightBorder, int number){
        if (leftBorder > rightBorder) {
            System.out.println("Элемент " + number + " не найден");
            return -1;
        }
        int middleNumber = (leftBorder + rightBorder) / 2;
        if (array[middleNumber] == number){
            System.out.println("Элемент " + number + " найден");
            return middleNumber;
        } else if(array[middleNumber] < number){
            return recursiveBinarySearch(array, middleNumber + 1, rightBorder, number);
        } else {
            return recursiveBinarySearch(array, leftBorder, middleNumber - 1, number);
        }
    }
}

/*
Реализовать алгоритм бинарного поиска двумя способами.
 */
