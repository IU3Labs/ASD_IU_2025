//Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению. Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
import java.util.Scanner;

public class TaskB1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] nums = new int[size];

        System.out.println("Введите числа: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }
        int result = findNumber(nums);
        if (result == -1) {
            System.out.println("Числа не найдены");
        } else {
            System.out.println(result);
        }

        scanner.close();
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int findNumber(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        int maxNum = findMax(nums);

        // массив для подсчета частот
        int[] frequency = new int[maxNum + 1];

        // подсчитываем частоты
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0 && nums[i] <= maxNum) {
                frequency[nums[i]]++;
            }
        }

        // ищем число, где значение равно частоте
        int result = -1;
        for (int i = 0; i < frequency.length; i++) {
            if (i == frequency[i]) {
                if (i > result) {
                    result = i;
                }
            }
        }

        return result;
    }

}

