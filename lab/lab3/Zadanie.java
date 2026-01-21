/* Дан целочисленный массив nums и целое число k,
 верните k наиболее часто встречающихся элементов.
 Вернуть ответ в любом порядке.Примечание.
 Сложность должна быть O(n*log(n)). Докажите сложность.

 Доказательство сложности: самая тяжелая часть кода-merge sort, а его сложность будет O(n*log(n))
*/
import java.util.Scanner;

public class Zadanie {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int N = scan.nextInt();

        int[] nums = new int[N];

        System.out.print("Введите число k: ");
        int k = scan.nextInt();

        for (int i = 0; i < N; i++) {
            System.out.print("Введите " + (i + 1) + "-й элемент массива: ");
            nums[i] = scan.nextInt();
        }

        mergeSortNums(nums, 0, N - 1);

        int[][] arr = new int[N][2];
        int number = 0;
        int count = 1;

        for (int i = 0; i < N - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                count++;
            } else {
                arr[number][0] = count;
                arr[number][1] = nums[i];
                number++;
                count = 1;
            }
        }

        arr[number][0] = count;
        arr[number][1] = nums[N - 1];
        number++;

        mergeSortArr(arr, 0, number - 1);

        System.out.println("Наиболее частые элементы:");

        int limit = Math.min(k, number);

        for (int i = 0; i < limit; i++) {
            System.out.println("Число " + arr[i][1] +
                    " встречается " + arr[i][0] + " раз(а)");
        }

        scan.close();
    }

    public static void mergeSortNums(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortNums(arr, left, mid);
            mergeSortNums(arr, mid + 1, right);

            mergeNums(arr, left, mid, right);
        }
    }

    public static void mergeNums(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSortArr(int[][] arr, int left, int right) {
        if (left < right) {

            int mid = (left + right) / 2;

            mergeSortArr(arr, left, mid);
            mergeSortArr(arr, mid + 1, right);

            mergeArr(arr, left, mid, right);
        }
    }

    public static void mergeArr(int[][] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[][] L = new int[n1][2];
        int[][] R = new int[n2][2];

        for (int i = 0; i < n1; i++) {
            L[i][0] = arr[left + i][0];
            L[i][1] = arr[left + i][1];
        }
        for (int j = 0; j < n2; j++) {
            R[j][0] = arr[mid + 1 + j][0];
            R[j][1] = arr[mid + 1 + j][1];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            if (L[i][0] >= R[j][0]) {
                arr[k][0] = L[i][0];
                arr[k][1] = L[i][1];
                i++;
            } else {
                arr[k][0] = R[j][0];
                arr[k][1] = R[j][1];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k][0] = L[i][0];
            arr[k][1] = L[i][1];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k][0] = R[j][0];
            arr[k][1] = R[j][1];
            j++;
            k++;
        }
    }
}
