//Дано K связанных списков. Слейте их в один полностью отсортированный список.
// Сложность O (N log K). Докажите сложность.

package lab3;
import java.util.*;

public class UniqueElementsFinder {

    // Счетчики для доказательства сложности
    private int treeInsertOperations = 0;
    private int treeSearchOperations = 0;
    private int totalElementsProcessed = 0;

    public static void main(String[] args) {
        UniqueElementsFinder finder = new UniqueElementsFinder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Поиск уникальных элементов в массиве ===");
        int[] array = finder.inputArrayManually(scanner);
        finder.analyzeAndFindUniques(array);

        scanner.close();
    }

    public void analyzeAndFindUniques(int[] array) {
        System.out.println("\n=== АНАЛИЗ МАССИВА ===");
        System.out.println("Размер массива (N): " + array.length);

        Set<Integer> uniqueCheck = new HashSet<>();
        for (int num : array) {
            uniqueCheck.add(num);
        }
        int u = uniqueCheck.size();
        System.out.println("Количество уникальных элементов (U): " + u);
        System.out.println("Ожидаемая сложность: O(N log U) = O(" + array.length + " × log " + u + ")");

        System.out.println("\nПРОЦЕСС ПОИСКА УНИКАЛЬНЫХ ЭЛЕМЕНТОВ");
        List<Integer> uniqueElements = findUniqueElementsWithProof(array);

        System.out.println("\nРЕЗУЛЬТАТЫ");
        System.out.println("Найдено уникальных элементов: " + uniqueElements.size());
        System.out.println("Отсортированный список уникальных элементов:");
        printResults(uniqueElements);
        printComplexityProof(array.length, u);
    }

    public List<Integer> findUniqueElementsWithProof(int[] array) {
        treeInsertOperations = 0;
        treeSearchOperations = 0;
        totalElementsProcessed = 0;

        TreeSet<Integer> uniqueSet = new TreeSet<>();

        for (int i = 0; i < array.length; i++) {
            totalElementsProcessed++;
            int currentElement = array[i];

            treeSearchOperations++;
            boolean exists = uniqueSet.contains(currentElement);

            if (!exists) {
                treeInsertOperations++;
                uniqueSet.add(currentElement);

                System.out.printf("Обработан элемент %d/%d: %d (добавлен в дерево)%n",
                        totalElementsProcessed, array.length, currentElement);
            } else {
                System.out.printf("Обработан элемент %d/%d: %d (дубликат)%n",
                        totalElementsProcessed, array.length, currentElement);
            }

            if (uniqueSet.size() > 0 && (i + 1) % Math.max(1, array.length / 5) == 0) {
                System.out.printf("  Размер дерева: %d, высота ~log(%d) = %.2f%n",
                        uniqueSet.size(), uniqueSet.size(), Math.log(uniqueSet.size()) / Math.log(2));
            }
        }

        return new ArrayList<>(uniqueSet);
    }

    private void printComplexityProof(int n, int u) {
        System.out.println("\nМАТЕМАТИЧЕСКОЕ ДОКАЗАТЕЛЬСТВО СЛОЖНОСТИ O(N log U)");

        int totalTreeOperations = treeInsertOperations + treeSearchOperations;
        double logU = Math.log(u) / Math.log(2); // log₂U

        System.out.println("\n1. ФАКТИЧЕСКИЕ ДАННЫЕ:");
        System.out.println("   N = " + n + " (всего элементов)");
        System.out.println("   U = " + u + " (уникальных элементов)");
        System.out.println("   Операций с деревом: " + totalTreeOperations +
                " (вставок: " + treeInsertOperations +
                ", поисков: " + treeSearchOperations + ")");

        System.out.println("\n2. АНАЛИЗ СЛОЖНОСТИ TreeSet:");
        System.out.println("   - contains() операция: O(log U)");
        System.out.println("   - add() операция: O(log U)");
        System.out.println("   - Высота красно-черного дерева: O(log U)");

        System.out.println("\n3. ТЕОРЕТИЧЕСКИЙ РАСЧЕТ:");
        System.out.println("   Для каждого из N элементов:");
        System.out.println("   - 1 операция поиска: O(log U)");
        System.out.println("   - В среднем U/N операций вставки: O(log U)");
        System.out.println("   Общая сложность: N × O(log U) = O(N log U)");

        System.out.println("\n4. ДЕТАЛЬНЫЙ РАСЧЕТ:");
        System.out.println("   Всего операций поиска: " + n + " × O(log " + u + ")");
        System.out.println("   Всего операций вставки: " + u + " × O(log " + u + ")");
        System.out.println("   Итого: O(" + n + " × log " + u + " + " + u + " × log " + u + ")");
        System.out.println("   Поскольку N ≥ U, упрощаем: O(N log U)");

        System.out.println("\n5. ВАЛИДАЦИЯ РЕЗУЛЬТАТА:");
        double theoreticalOperations = n * logU;
        System.out.println("   Теоретическая оценка: " + String.format("%.2f", theoreticalOperations) + " операций");
        System.out.println("   Фактическое количество: " + totalTreeOperations + " операций");
        System.out.println("   Отношение: " + String.format("%.2f", (double)totalTreeOperations / theoreticalOperations));
    }

    private int[] inputArrayManually(Scanner scanner) {
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        while (size <= 0) {
            System.out.print("Размер должен быть положительным. Введите снова: ");
            size = scanner.nextInt();
        }

        int[] array = new int[size];
        System.out.println("Введите " + size + " элементов массива:");

        for (int i = 0; i < size; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        return array;
    }

    private void printResults(List<Integer> uniqueElements) {
        if (uniqueElements.size() <= 50) {
            for (int i = 0; i < uniqueElements.size(); i++) {
                System.out.print(uniqueElements.get(i));
                if (i < uniqueElements.size() - 1) {
                    System.out.print(", ");
                }
                if ((i + 1) % 10 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        } else {
            System.out.println("Первые 20 элементов:");
            for (int i = 0; i < 20; i++) {
                System.out.print(uniqueElements.get(i) + " ");
            }
            System.out.println("\n... и еще " + (uniqueElements.size() - 20) + " элементов");

            System.out.println("Последние 5 элементов:");
            for (int i = uniqueElements.size() - 5; i < uniqueElements.size(); i++) {
                System.out.print(uniqueElements.get(i) + " ");
            }
            System.out.println();
        }
    }
}