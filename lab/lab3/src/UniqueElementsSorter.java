import java.util.*;

public class UniqueElementsSorter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод количества элементов
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();

        // Ввод элементов массива
        System.out.println("Введите " + n + " целых чисел:");
        int[] elements = new int[n];
        for (int i = 0; i < n; i++) {
            elements[i] = scanner.nextInt();
        }
        scanner.close();

        // Обработка - поиск уникальных элементов
        TreeSet<Integer> uniqueElements = new TreeSet<>();
        for (int element : elements) {
            uniqueElements.add(element);
        }

        // Вывод результатов
        System.out.println("Уникальные элементы в отсортированном порядке:");
        for (int element : uniqueElements) {
            System.out.print(element + " ");
        }
    }
}

/**TreeSet реализован как красно-черное дерево - сбалансированное двоичное дерево поиска.

Принцип работы:

Высота сбалансированного дерева с U узлами: O(log U)

Каждая операция (вставка, поиск, удаление) требует обхода от корня до листа

Максимальное количество сравнений: пропорционально высоте дерева

Математически:

Дерево с U узлами имеет высоту ∼log₂U

Каждая операция: O(log U) сравнений

N операций вставки: O(N log U)     */