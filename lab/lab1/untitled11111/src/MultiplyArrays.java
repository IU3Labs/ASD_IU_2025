/* Пусть любое число – это массив его цифр слева направо. Пример, число
1234 – это массив [1,2,3,4].
Дан массив целых чисел. Реализовать умножение двух чисел.
Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
Результат – число, представленное массивом.*/

public class MultiplyArrays {
    public static void main(String[] args) {
        System.out.println("Введите первое число: ");
        int[] num1 = Additionals.inputNumberAsDigitArray();
        System.out.println("Введите второе число: ");
        int[] num2 = Additionals.inputNumberAsDigitArray();

        int[] result = multiply(num1, num2);
        System.out.println("Результат: ");
        Additionals.printArrayAsList(result);

    }


    public static int[] multiply(int[] num1, int[] num2) {
        int n = num1.length;
        int m = num2.length;
        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int prod = num1[i] * num2[j];
                int posLow = i + j + 1;
                int posHigh = i + j;

                prod += result[posLow];
                result[posLow] = prod % 10;
                result[posHigh] += prod / 10;
            }
        }

        int check = 0;
        while (check < result.length - 1 && result[check] == 0) {
            check++;
        }

        int[] res = new int[result.length - check];
        System.arraycopy(result, check, res, 0, res.length); //откуда, с какого индекса, куда, с какого индекса, сколько
        return res;
    }

}
