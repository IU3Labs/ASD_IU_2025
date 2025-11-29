import tools.Tools;

import java.util.Scanner;

public class TaskB3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Tools.getArray(scanner);

        System.out.println(sumOfTwoSmallestPositive(arr));
    }

    public static int sumOfTwoSmallestPositive(int[] arr) {
        if (arr.length < 5) {
            return -1;
        }
        
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i > 0) {
                if (i < firstMin) {
                    secondMin = firstMin;
                    firstMin = i;
                } else if (i < secondMin) {
                    secondMin = i;
                }
            }
        }
        
        if (firstMin == Integer.MAX_VALUE || secondMin == Integer.MAX_VALUE) {
            return -1;
        }
        
        return firstMin + secondMin;
    }
}