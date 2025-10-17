import java.util.Scanner;

/*Дан целочисленный массив. Верните число, частота встречи которого в
массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
таких чисел несколько, вернуть наибольшее.*/

public class FrequencyEqualsValue {

    public static void main(String[] args) {
        int[] nums = inputArray();
        int result = findNum(nums);
        System.out.println("Ответ:" + result);
    }

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива:");
        int size = scanner.nextInt();
        if (size < 5) {
            System.out.println("Размер массива должен быть больше 5, введите еще раз:");
            size = scanner.nextInt();
        }

        int[] arr = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            int value = scanner.nextInt();
            arr[i] = value;
        }

        return arr;
    }

    public static int findNum(int[] arr) {
        int res = -1;

        for (int i = 0; i < arr.length; i++) { //перебор всех чисел массива
            int current = arr[i];
            int count = 0;
            for (int j = 0; j < arr.length; j++) {//счетчик этого числа в массиве
                if (arr[j] == current) {
                    count += 1;
                }
            }
            if (count == current && current > res) {
                res = current;
            }
        }
        return res;
    }
}
