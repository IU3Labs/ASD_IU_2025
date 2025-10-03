package lab1;

// Пусть любое число – это массив его цифр слева направо. Пример, число
// 1234 – это массив [1,2,3,4].
// Дан массив целых чисел. Реализовать умножение двух чисел.
// Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
// Результат – число, представленное массивом.

public class SecondTaskGroup {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int arr_2[] = {1, 2, 3};

        int num_1 = translateToNumbers(arr);
        int num_2 = translateToNumbers(arr_2);

        int product = num_1 * num_2; // умножение
        System.out.println("Произведение: " + product);

        int[] resultArray = numberToArray(product);

        System.out.print("[");
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[i]);
            if (i < resultArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }


    public static int translateToNumbers(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str = str + arr[i];
        }
        int num = Integer.parseInt(str);
        return num;
    }


    public static int[] numberToArray(int num) {
        String str = String.valueOf(num);
        int[] arr = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
        }

        return arr;
    }
}
