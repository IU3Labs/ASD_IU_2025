package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ArrayTools {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] createIntArray() throws IOException {
        System.out.print("Добро пожаловать, мой господин!\nВведите размер массива: ");
        int sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        ArrayTools.fillArray(sizeOfArray, array);
        return array;
    }

    public static double[] createDoubleArray() throws IOException {
        System.out.print("Добро пожаловать, мой господин!\nВведите размер массива: ");
        int sizeOfArray = scanner.nextInt();
        double[] array = new double[sizeOfArray];
        ArrayTools.fillArray(sizeOfArray, array);
        return array;
    }

    public static void fillArray(int sizeOfArray, double[] array) throws IOException {
        for (int i = 0; i < sizeOfArray; i++) {
            System.out.print("Введите " + (i + 1) + " элемент массива: ");
            array[i] = Double.parseDouble(reader.readLine());
        }
    }

    public static void fillArray(int sizeOfArray, int[] array) throws IOException {
        for (int i = 0; i < sizeOfArray; i++) {
            System.out.print("Введите " + (i + 1) + " элемент массива: ");
            array[i] = Integer.parseInt(reader.readLine());
        }
    }


    public static void sorterAscArray(double[] array) {
        double arrayLength = array.length;
        for (int i = 1; i < arrayLength; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arrayLength - i; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

}
