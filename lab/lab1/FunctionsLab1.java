package lab1;

import java.util.Scanner;

public class FunctionsLab1 {
    public static final Scanner in = new Scanner(System.in);

    // метод перевода дв. в дес.
    public static int boolToInt(int[] psevdoBoolList, int len) {
        // len нужно чтобы обрезать лишние битики из массива (даже если там нули, они влияют)
        int result = 0;
        for (byte i = 0; i < len; i++) {
            result += (psevdoBoolList[i] * (Math.pow(2, len - i - 1))); // формулка
        }
        return result;
    }

    // метод ввода int массива
    public static int[] inIntList() {
        System.out.println("Massive's lenth: ");
        int lenn = in.nextInt();
        System.out.println("Enter elements: ");
        int[] inputt = new int[lenn]; // данный массив
        for (int i = 0; i < lenn; i++) {
            inputt[i] = in.nextInt();
        }
        return inputt;
    }

    // sorting
    public static void bubbleSort(int[] sortArr){
        for (int i = 0; i < sortArr.length - 1; i++) {
            for(int j = 0; j < sortArr.length - i - 1; j++) {
                if(sortArr[j + 1] < sortArr[j]) {
                    int swap = sortArr[j];
                    sortArr[j] = sortArr[j + 1];
                    sortArr[j + 1] = swap;
                }
            }
        }
    }

    // методы вывода массива
    public static void print(boolean[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.print(massive[i] + " ");
        }
        System.out.println('\n');
    }

    public static void print(int[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.print(massive[i] + " ");
        }
        System.out.println('\n');
    }

    public static void print(byte[] massive) {
        for (int i = 0; i < massive.length; i++) {
            System.out.print(massive[i] + " ");
        }
        System.out.println('\n');
    }

    // метод ввода бин. массива
    public static byte[] inByteList() {
        System.out.println("Massive's lenth: ");
        int lenn = in.nextInt();
        System.out.println("Enter elements: ");
        byte[] inputt = new byte[lenn]; // данный массив 0 и 1
        for (int i = 0; i < lenn; i++) {
            inputt[i] = (byte) in.nextInt();
        }
        return inputt;
    }

    // метод ввода числа n
    public static int inNumber() {
        System.out.println("Enter n: ");
        int n = in.nextInt();
        return n;
    }

    // методы проверки элемента на наличие в списке
    public static boolean isElInList(byte[] massive, byte el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return true;
            }
        }
        return false;
    }

    public static int isElInListWithId(byte[] massive, int el) {
        for (int j = 0; j < massive.length; j++) {
            if (massive[j] == el) {
                return j;
            }
        }
        return -1;
    }

}
