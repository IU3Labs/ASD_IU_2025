import java.util.Scanner;

public class Additionals {

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int[] inputArrayMinSize(int minSize) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива (минимум " + minSize + "): ");
        int size = scanner.nextInt();
        while (size < minSize) {
            System.out.print("Размер должен быть не меньше " + minSize + ". Повторите ввод: ");
            size = scanner.nextInt();
        }

        int[] array = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int[] inputNumberAsDigitArray() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("Ошибка: строка пустая. Попробуйте снова.");
                continue;
            }

            boolean isValid = true;
            for (char c : line.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Ошибка: в строке есть недопустимые символы. Используйте только цифры.");
                continue;
            }

            int[] digits = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                digits[i] = line.charAt(i) - '0';
            }
            return digits;
        }
    }

    public static void printArrayAsList(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }


    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }


}