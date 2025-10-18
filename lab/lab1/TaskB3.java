import java.util.Scanner;

public class TaskB3 {

    public static int sumOfTwoSmallestPositive(int[] arr) {
        if (arr.length < 5) {
            return -1;
        }
        
        int firstMin = -1;
        int secondMin = -1;

        for (int num : arr) {
            if (num > 0) {
                if (num < firstMin) {
                    secondMin = firstMin;
                    firstMin = num;     
                } 
                else if (num < secondMin) {
                    secondMin = num;     
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