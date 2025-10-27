import java.util.Scanner;

public class InversionCount {

    public static int countInversions(int[] arr) {
        int count = 0; // количество инверсий

        // перебираем все элементы как потенциальные первые элементы пар
        for (int i = 0; i < arr.length-1; i++) {
            //перебираем все элементы справа от текущего
            for (int j = i + 1; j < arr.length; j++) {
                // если текущий элемент больше элемента справа - это инверсия
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Введите элемент массива: ");
            arr[i] = scanner.nextInt();
        }

        System.out.println(countInversions(arr));

        scanner.close();
    }
}

/*
Временная сложность: O(n²)
внешний цикл выполняется n раз
внутренний цикл выполняется в среднем n/2 раз для каждой итерации внешнего цикла
общее количество операций: n * (n/2) = n²/2 = O(n²)
*/