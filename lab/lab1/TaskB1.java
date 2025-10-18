import java.util.Scanner;

public class TaskB1 {

    public static int findNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        bubbleSort(arr);
        
        int result = -1;
        int currentNumber = arr[0];
        int count = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == currentNumber) {
                count++;
            } else {
                if (currentNumber >= 0 && currentNumber == count) {
                    if (currentNumber > result) {
                        result = currentNumber;
                    }
                }
                currentNumber = arr[i];
                count = 1;
            }
        }
        
        if (currentNumber >= 0 && currentNumber == count) {
            if (currentNumber > result) {
                result = currentNumber;
            }
        }
        
        return result;
    }
    
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
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
        
        int result = findNumber(arr);
        System.out.println("Результат: " + result);
        
        scanner.close();
    }
}