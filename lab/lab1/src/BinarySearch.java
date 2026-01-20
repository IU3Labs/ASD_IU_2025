/*
Реализовать алгоритм бинарного поиска двумя способами:
- итеративным;
- рекурсивным.
*/
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    public int input(){
        Scanner scanner =  new Scanner(System.in);
        int res = scanner.nextInt();
        return res;
    }

    int[] inputArray() {
        System.out.print("Enter array size: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter number (" + (i + 1) + "/" + size + "): ");
            a[i] = input();
        }
        return a;
    }



    void printArray(int[] a){
        for (int j : a) System.out.print(j + " ");
    }

    int binarySearch(int[] a, int t){
        Arrays.sort(a);
        int start = 0;
        int end = a.length - 1;

        while (start <= end){
            int mid = (start + end) / 2;

            if (a[mid] == t){
                return  mid;
            }else if(a[mid] > t){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
    int binarySearchRecursive(int[] arr, int t, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (arr[mid] == t) {
            return mid;
        } else if (arr[mid] > t) {
            return binarySearchRecursive(arr, t, start, mid - 1);
        } else {
            return binarySearchRecursive(arr, t, mid + 1, end);
        }
    }

    void main() {
        int[] a = inputArray();
        System.out.print("Enter binary search target: ");
        int t = input();
        System.out.println("Result for simple binary: " + binarySearch(a, t));
        System.out.println("Result for recursive binary: " + binarySearchRecursive(a, t,0, a.length - 1 ));


    }
}