import java.util.Scanner;

// Реализовать алгоритм бинарного поиска (итеративный способ)
class BinarySearchIterative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы отсортированного массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
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
        int[] array = new int[n];
        System.out.println("Введите элементы отсортированного массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
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

// Дан целочисленный массив. Верните число, частота встречи которого в массиве равна его значению. Если таких чисел нет, вернуть «-1». Если таких чисел несколько, вернуть наибольшее.
class GroupBTask1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
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

// Пусть любое число – это массив его цифр слева направо. Дан массив целых чисел. Реализовать умножение двух чисел. Результат – число, представленное массивом.
class GroupBTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество цифр для первого числа: ");
        int m = scanner.nextInt();
        int[] num1 = new int[m];
        System.out.println("Введите цифры первого числа (от старшего к младшему):");
        for (int i = 0; i < m; i++) {
            num1[i] = scanner.nextInt();
        }
        System.out.print("Введите количество цифр для второго числа: ");
        int n = scanner.nextInt();
        int[] num2 = new int[n];
        System.out.println("Введите цифры второго числа (от старшего к младшему):");
        for (int i = 0; i < n; i++) {
            num2[i] = scanner.nextInt();
        }
        int[] result = multiply(num1, num2);
        printArray(result);
    }

    private static int[] multiply(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;
        int[] rev1 = new int[m];
        for (int i = 0; i < m; i++) {
            rev1[i] = num1[m - 1 - i];
        }
        int[] rev2 = new int[n];
        for (int i = 0; i < n; i++) {
            rev2[i] = num2[n - 1 - i];
        }
        int[] result = new int[m + n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i + j] += rev1[i] * rev2[j];
            }
        }
        int carry = 0;
        for (int i = 0; i < m + n; i++) {
            int sum = result[i] + carry;
            result[i] = sum % 10;
            carry = sum / 10;
        }
        int[] finalResult = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            finalResult[i] = result[m + n - 1 - i];
        }
        int start = 0;
        while (start < m + n - 1 && finalResult[start] == 0) {
            start++;
        }
        int[] trimmed = new int[m + n - start];
        for (int i = 0; i < trimmed.length; i++) {
            trimmed[i] = finalResult[start + i];
        }
        return trimmed;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

// Дан массив целых чисел. Минимальное количество элементов – 5. Вернуть число, которое является суммой двух наименьших положительных чисел.
class GroupBTask3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов (не менее 5): ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int sum = sumOfTwoSmallestPositive(array);
        System.out.println(sum);
    }

    private static int sumOfTwoSmallestPositive(int[] array) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : array) {
            if (num > 0) {
                if (num < min1) {
                    min2 = min1;
                    min1 = num;
                } else if (num < min2 && num > min1) {
                    min2 = num;
                }
            }
        }
        return min1 + min2;
    }
}