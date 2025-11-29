import java.util.Scanner;

class GroupBTask3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов (не менее 5): ");
        int n = scanner.nextInt();
        int[] array = ArrayUtility.fillArray(scanner, n, "Введите элементы массива:");
        int sum = sumOfTwoSmallest(array);
        System.out.println(sum);
    }

    private static int sumOfTwoSmallest(int[] array) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : array) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        if (min2 == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("В массиве менее двух элементов");
        }

        return min1 + min2;
    }
}