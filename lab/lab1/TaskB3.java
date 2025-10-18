import java.util.Scanner;

public class TaskB3 {

    public static int sumOfTwoSmallestPositive(int[] arr) {
        if (arr.length < 5) {
            return -1;
        }
        
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        int n = arr.length;
        for (int num = 0; num < n; num++) {
            if (arr[num] > 0) {
                if (arr[num] < firstMin) {
                    secondMin = firstMin;
                    firstMin = arr[num];
                } 
                else if (arr[num] < secondMin) {
                    secondMin = arr[num];
                }
            }
        }
        
        if (firstMin == Integer.MAX_VALUE || secondMin == Integer.MAX_VALUE) {
            return -1;
        }
        
        return firstMin + secondMin;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        
        int[] arr = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        
        int result = sumOfTwoSmallestPositive(arr);
        System.out.println(result);
        
        scanner.close();
    }
}