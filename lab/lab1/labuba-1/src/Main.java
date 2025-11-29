/*
Задание:
    Реализовать алгоритм бинарного поиска двумя способами.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] sortedArr = inputArray(scan);
        sortArray(sortedArr);
        printArray(sortedArr);

        int isc = inputSearchNumber(scan);
        scan.close();

        searchNumbers(sortedArr, isc);
    }

    static int[] inputArray(Scanner scan) {
        System.out.print("Введите длину массива: ");
        int len = scan.nextInt();
        int[] sortedArr = new int[len];

        for (int i = 0; i < len; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент массива: ");
            sortedArr[i] = scan.nextInt();
        }
        return sortedArr;
    }


    static void sortArray(int[] sortedArr) {
        int len = sortedArr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (sortedArr[j + 1] < sortedArr[j]) {
                    int a = sortedArr[j];
                    sortedArr[j] = sortedArr[j + 1];
                    sortedArr[j + 1] = a;
                }
            }
        }
    }

    static void printArray(int[] sortedArr) {
        for (int i = 0; i < sortedArr.length; i++) {
            System.out.println(sortedArr[i]);
        }
    }

    static int inputSearchNumber(Scanner scan) {
        System.out.print("Введите искомое число: ");
        return scan.nextInt();
    }

    static void searchNumbers(int[] sortedArr, int isc) {
        int answer = bsearch(sortedArr, isc);
        System.out.println("Искомое число находится под номером: " + (answer + 1));

        answer = recBinSearch(sortedArr, isc, 0, sortedArr.length - 1);
        System.out.println("Искомое число находится под номером: " + (answer + 1));
    }

    static int bsearch(int[] arr, int isc) {
        int leftBorder = 0;
        int rightBorder = arr.length - 1;
        int indicator;

        while (leftBorder <= rightBorder) {
            indicator = leftBorder + (rightBorder - leftBorder) / 2;

            if (arr[indicator] == isc) {
                return indicator;
            }

            if (arr[indicator] < isc) {
                leftBorder = indicator + 1;
            } else {
                rightBorder = indicator - 1;
            }
        }
        return -1;
    }

    static int recBinSearch(int[] arr, int isc, int leftBorder, int rightBorder) {
        if (leftBorder > rightBorder) {
            return -1;
        }

        int indicator = leftBorder + (rightBorder - leftBorder) / 2;

        if (arr[indicator] == isc) {
            return indicator;
        }

        if (arr[indicator] < isc) {
            return recBinSearch(arr, isc, indicator + 1, rightBorder);
        } else {
            return recBinSearch(arr, isc, leftBorder, indicator - 1);
        }
    }
}