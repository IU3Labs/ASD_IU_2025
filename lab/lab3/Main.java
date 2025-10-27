import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите задание для выполнения:");
        System.out.println("1 - Количество инверсий в массиве");
        System.out.println("2 - K наиболее частых элементов");
        System.out.println("3 - Минимальный элемент в циклически сдвинутом массиве");
        System.out.println("4 - K наиболее частых элементов (продвинутая версия)");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                groupa.InversionCount.main(args);
                break;
            case 2:
                groupa.TopKFrequentElements.main(args);
                break;
            case 3:
                groupa.FindMinInRotatedArray.main(args);
                break;
            case 4:
                groupb.TopKFrequentAdvanced.main(args);
                break;
            default:
                System.out.println("Неверный выбор");
        }

        scanner.close();
    }
}