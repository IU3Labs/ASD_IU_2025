import java.util.*;

public class InversionCounter {

    /**
     * Основной метод для подсчета и вывода всех инверсий в массиве
     * @param arr исходный массив
     */
    public static void findAndPrintInversions(int[] arr) {
        System.out.println("\nИсходный массив: " + Arrays.toString(arr));
        System.out.println("Поиск инверсий...");

        List<String> inversions = new ArrayList<>();
        int inversionCount = 0;

        // Перебираем все возможные пары индексов (i, j) где i < j
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // Проверяем условие инверсии: i < j и arr[i] > arr[j]
                if (arr[i] > arr[j]) {
                    inversionCount++;
                    String inversion = String.format("(%d, %d) -> [%d > %d]",
                            i, j, arr[i], arr[j]);
                    inversions.add(inversion);

                    System.out.println("Найдена инверсия: " + inversion);
                }
            }
        }

        // Выводим итоговую информацию
        System.out.println("\n=== РЕЗУЛЬТАТЫ ===");
        System.out.println("Общее количество инверсий: " + inversionCount);

        if (inversionCount > 0) {
            System.out.println("\nВсе инверсии:");
            for (String inversion : inversions) {
                System.out.println(inversion);
            }
        } else {
            System.out.println("Инверсий не найдено - массив отсортирован по возрастанию!");
        }

        // Анализ сложности
        System.out.println("\n=== АНАЛИЗ СЛОЖНОСТИ ===");
        System.out.println("Временная сложность: O(n²)");
        System.out.println("Пространственная сложность: O(k), где k - количество инверсий");
    }

    /**
     * Метод для ввода массива от пользователя
     */
    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ПОДСЧЕТ ИНВЕРСИЙ В МАССИВЕ ===");
        System.out.println("Инверсия - пара индексов (i, j), где i < j и arr[i] > arr[j]");
        System.out.println("\nВведите элементы массива через пробел:");

        try {
            String input = scanner.nextLine();
            String[] elements = input.split(" ");
            int[] arr = new int[elements.length];

            for (int i = 0; i < elements.length; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }

            return arr;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: пожалуйста, вводите только целые числа!");
            return null;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("ПРОГРАММА ДЛЯ ПОДСЧЕТА ИНВЕРСИЙ В МАССИВЕ");
        System.out.println("=========================================");

        while (continueProgram) {
            int[] arr = inputArray();

            if (arr != null) {
                findAndPrintInversions(arr);
            }

        }

        scanner.close();
    }
}

/**Временная сложность: O(n²)

В худшем случае (обратно отсортированный массив) мы проверяем все возможные пары

Количество пар: n(n-1)/2 ≈ n²/2 → O(n²)

Пространственная сложность: O(k)

Где k - количество инверсий

Мы храним информацию о каждой инверсии в списке
 */