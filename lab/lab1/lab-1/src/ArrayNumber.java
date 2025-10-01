import java.util.Scanner;

public class ArrayNumber {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Введите длину первого массива: ");
        int length1 = scanner.nextInt();
        int[] array1 = Arrays.newArray(length1);

        System.out.print("Введите длину второго массива: ");
        int length2 = scanner.nextInt();
        int[] array2 = Arrays.newArray(length2);

        System.out.println("Результат произведения: ");

        Arrays.printArray(numToArr(arrToNum(array1) * arrToNum(array2)));
    }

    private static int arrToNum(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += (int) (array[i] * Math.pow(10, array.length - i - 1));
        }
        return result;
    }

    private static int[] numToArr(int number) {
        int temp = number;
        int count = 0;
        while (temp > 0) {
            temp /= 10;
            count++;
        }

        int[] array = new int[count];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = number % 10;
            number /= 10;
        }
        return array;
    }
}

