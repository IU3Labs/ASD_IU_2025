import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите задачу для выполнения:");
        System.out.println("1 - Бинарный поиск (итеративный)");
        System.out.println("2 - Бинарный поиск (рекурсивный)");
        System.out.println("3 - Римские в арабские числа");
        System.out.println("4 - Решето Эратосфена");
        System.out.println("5 - Число с равной частотой");
        System.out.println("6 - Умножение чисел как массивов");
        System.out.println("7 - Сумма двух наименьших положительных");
        System.out.println("8 - Два числа с заданной суммой");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                binarysearch.BinarySearchIterative.main(args);
                break;
            case 2:
                binarysearch.BinarySearchRecursive.main(args);
                break;
            case 3:
                groupa.RomanToArabic.main(args);
                break;
            case 5:
                groupb.FrequencyNumber.main(args);
                break;
            case 6:
                groupb.ArrayMultiplication.main(args);
                break;
            case 7:
                groupb.SumTwoSmallest.main(args);
                break;
            case 8:
                groupb.TwoSum.main(args);
                break;
            default:
                System.out.println("Неверный выбор");
        }

        scanner.close();
    }
}