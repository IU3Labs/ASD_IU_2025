import java.util.Scanner;

// Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению. Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
class GroupBTask1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        int[] array = ArrayUtility.fillArray(scanner, n, "Введите элементы массива:");
        int result = findNumberWithFreqEqualValue(array);
        System.out.println(result);
    }

    private static int findNumberWithFreqEqualValue(int[] array) {
        int max = -1;
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            if (num <= 0) {
                continue;
            }
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == num) {
                    count++;
                }
            }
            if (count == num && num > max) {
                max = num;
            }
        }
        return max;
    }
}