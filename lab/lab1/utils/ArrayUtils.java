package utils;
import java.util.Scanner;


public class ArrayUtils {
    public static void fillArray(int[] array, Scanner scan){
        for (int i = 0; i < array.length; i++) {
            System.out.println("Введите " + (i+1) + " элемент: ");
            array[i] = scan.nextInt();
        }
    }
}
