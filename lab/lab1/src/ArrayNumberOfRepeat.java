/*
Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.
 */

import java.util.Scanner;

public class ArrayNumberOfRepeat {
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

    public int input(){
        Scanner scanner =  new Scanner(System.in);
        int res = scanner.nextInt();
        return res;
    }

    void printArray(int[] a){
        for (int j : a) System.out.print(j + " ");
    }

    int findMostOften(int[] a){
        int ans = -1;

        for( int i = 0; i < a.length; i++){
            int target = a[i];
            int counter = 0;
            if (target == ans) continue;
            for (int j : a)
                if (target == j) counter++;
            if (counter == target){
                ans = Integer.max(ans, target);
            }
        }
        return ans;
    }

    void main() {
        int[] a = inputArray();
        System.out.println("Answer: " + findMostOften(a));
    }
}
