import java.util.Scanner;

// Реализовать алгоритм бинарного поиска (итеративный способ)
class BinarySearchIterative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        int[] array = ArrayUtility.fillArray(scanner, n, "Введите элементы отсортированного массива:");
        System.out.print("Введите цель поиска: ");
        int target = scanner.nextInt();
        int result = binarySearch(array, target);
        if (result == -1) {
            System.out.println("Не найдено");
        } else {
            System.out.println("Найдено по индексу " + result);
        }
    }

    private static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}

// Реализовать алгоритм бинарного поиска (рекурсивный способ)
class BinarySearchRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        int[] array = ArrayUtility.fillArray(scanner, n, "Введите элементы отсортированного массива:");
        System.out.print("Введите цель поиска: ");
        int target = scanner.nextInt();
        int result = binarySearch(array, target, 0, array.length - 1);
        if (result == -1) {
            System.out.println("Не найдено");
        } else {
            System.out.println("Найдено по индексу " + result);
        }
    }

    private static int binarySearch(int[] array, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearch(array, target, mid + 1, high);
        } else {
            return binarySearch(array, target, low, mid - 1);
        }
    }
}