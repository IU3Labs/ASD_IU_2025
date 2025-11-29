/*Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
число, которое является суммой двух наименьших положительных чисел. */

public class SumTwoPositives {
    public static void main(String[] args) {
        int[] nums = Additionals.inputArrayMinSize(5);

        int res = findSum(nums);
        if (res == -1) {
            System.out.println("Меньше двух положительных чисел, не удалось найти сумму.");
        } else {
            System.out.println("Сумма наименьших положительных чисел:" + res);
        }
    }


    public static int findSum(int[] arr) {
        Integer min1 = null, min2 = null;

        for (int num : arr) {
            if (num > 0) {
                if (min1 == null || num < min1) {
                    min2 = min1;
                    min1 = num;
                } else if (min2 == null || num < min2) {
                    min2 = num;
                }
            }
        }

        if (min1 == null || min2 == null) {
            return -1;
        }

        return min1 + min2;

    }

}
