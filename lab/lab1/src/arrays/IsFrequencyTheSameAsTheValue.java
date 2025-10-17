// Группа Б, Задача 1
// Дан целочисленный массив. Верните число, частота встречи которого в
//массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
//таких чисел несколько, вернуть наибольшее.

package arrays;

import java.util.Scanner;

public class IsFrequencyTheSameAsTheValue {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int arr[] = makeArray(scanner);
        int arr_coin[] = checkCoincidence(arr);
        printResults(arr_coin);
        scanner.close();
    }

    private static int[] makeArray(Scanner scanner) {
        int num_of_elements;
        System.out.println("Введите число элементов массива:");
        num_of_elements = scanner.nextInt();
        int arr[] = new int[num_of_elements+1];
        arr[0] = num_of_elements;
        System.out.println("Введите элементы массива:");
        int input;

        for (int i = 1; i <= num_of_elements; i++){
            input = scanner.nextInt();
            arr[i] = input;
        }

        return arr;
    }

    private static int[] checkCoincidence(int arr[]){
        int num_of_elements = arr[0];
        int arr_coin[] = new int[num_of_elements+1];
        arr_coin[0] = num_of_elements;
        for (int i = 1; i <= num_of_elements; i++){
            arr_coin[i] = -1;
        }

        for (int i = 1; i <= num_of_elements; i++){
            int num = arr[i];
            int total = 0;
            for (int j = 1; j <= num_of_elements; j++){
                if (arr[j] == num){
                    total += 1;
                }
            }
            if (total == num){
                arr_coin[i] = num;
            }
        }

        return arr_coin;
    }

    private static void printResults(int[] arr_coin){
        int max_value = -1;

        for (int i = 1; i <= arr_coin[0]; i++){
            if (arr_coin[i] > max_value){
                max_value = arr_coin[i];
            }
        }

        System.out.println(max_value);
    }
}
