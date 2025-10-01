/* Методы для работы с консолью */

import java.util.Scanner;

public class InpOutMethods {

    private static final Scanner scaner = new Scanner(System.in);

    public static int getInt(String consoleValue) {
        System.out.print(consoleValue);
        return scaner.nextInt();
    }

    public static String getString(String consoleValue) {
        System.out.print(consoleValue);
        return scaner.nextLine();
    }

    public static int[] getArray() {
        int size = InpOutMethods.getInt("Введите размер массива ");
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = InpOutMethods.getInt("Введите " + (i + 1) + "-й элемент ");
        }
        return array;
    }

    public static void printArray(int[] array) {
         for (int i = 0; i < array.length; i++) {
             System.out.print(array[i]);
             if (array.length - 1 > i) {
                 System.out.print(", ");
             }
         }
    }

    public static String[] getStrArray() {
        int size = InpOutMethods.getInt("Введите размер массива ");
        String[] array = new String[size];

        for (int i = 0; i < size; i++) {
            array[i] = InpOutMethods.getString("Введите " + (i + 1) + "-й элемент ");
        }
        return array;
    }

    public static void printBoolArray(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (array.length - 1 > i) {
                System.out.print(", ");
            }
        }
    }

}