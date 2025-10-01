import java.util.Scanner;

public class Arrays {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] newArray(int length) {

        if (length <= 0) {
            System.out.println("Неверное число элементов. ");
            return null;
        } else {
            int[] array = new int[length];

            for (int i = 0; i < length; i++) {
                System.out.print("Введите " + (i + 1) + "-ый элемент: ");
                array[i] = scanner.nextInt();
            }
            return array;
        }
    }

    public static void printArray(int[] array) {

        if (array == null || array.length <= 0) {
            System.out.println("Плохая шутка");
        } else if (array.length > 0) {
            System.out.print("Запрошенный массив: ");
            System.out.print("[");

            for (int i = 0; i < array.length; i++) {
                if (i >= 0 && i != array.length - 1) {
                    System.out.print(array[i] + ", ");
                } else {
                    System.out.println(array[i] + "]");
                }
            }
        }
    }

    public static int[] arraySort(int[] array) {
        if (array == null || array.length <= 0) {
            System.out.println("Некорректное число элементов для сортировки. ");
        } else {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {

                    if (array[i] > array[j]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    } else {
                        continue;
                    }
                }
            }
        }

        return array;
    }
}
