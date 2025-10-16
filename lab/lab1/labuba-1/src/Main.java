
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Введите длину массива: ");
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        int[] sortedArr = new int[len];

        for (int i = 0; i < len; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент массива: ");
            sortedArr[i] = scan.nextInt();
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (sortedArr[j + 1] < sortedArr[j]) {
                    int a = sortedArr[j];
                    sortedArr[j] = sortedArr[j + 1];
                    sortedArr[j + 1] = a;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println(sortedArr[i]);
        }

        System.out.print("Введите искомое число: ");
        int isc = scan.nextInt();
        scan.close();

        int answer = bsearch(sortedArr, isc);
        System.out.println("Искомое число находится под номером: " + (answer + 1));

        answer = recBinSearch(sortedArr, isc, 0, sortedArr.length - 1);
        System.out.println("Искомое число находится под номером: " + (answer + 1));
    }

    private static int bsearch(int[] arr, int isc) {
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

    private static int recBinSearch(int[] arr, int isc, int leftBorder, int rightBorder) {
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
