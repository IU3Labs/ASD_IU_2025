// Группа Б. Дан массив целых чисел, представляющий двоичное число.
//Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
//выглядит так: arr = [1, 3, 6]. То есть:
//        • arr[0] = bi_arr[0] = 1(2) = 1(10),
//        • arr[1] = bi_arr[0] bi_arr[1] =11(2) = 3(10),
//        • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] =110(2) = 6(10)
//Так же дано целое положительное число – n. Вернуть массив Boolean, где
//true – число делится на N, false – нет.
//        Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
//так: [false, false, true].
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] binaryArray = ArrayUtils.inputArray(scanner);

        System.out.print("Введите число n: ");
        int n = scanner.nextInt();

        boolean[] result = checkDivision(binaryArray, n);

        System.out.print("Результат: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static boolean[] checkDivision(int[] binaryArray, int n) {
        boolean[] result = new boolean[binaryArray.length];
        int currentNumber = 0;

        for (int i = 0; i < binaryArray.length; i++) {
            currentNumber = (currentNumber << 1) | binaryArray[i];
            result[i] = (currentNumber % n == 0);
        }

        return result;
    }

}