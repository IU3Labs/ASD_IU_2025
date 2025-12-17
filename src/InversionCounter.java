import java.util.Scanner;
import java.util.ArrayList;

/*
Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность.
 */
public class InversionCounter {

    private static int[] readArrayFromInput() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Введите количество элементов в массиве: ");
        int arraySize = inputScanner.nextInt();
        int[] numbers = new int[arraySize];

        System.out.println("Введите элементы массива:");
        for (int index = 0; index < arraySize; index++) {
            System.out.print("Элемент " + index + ": ");
            numbers[index] = inputScanner.nextInt();
        }

        inputScanner.close();
        return numbers;
    }

    /**
     * Метод для поиска всех инверсий в массиве
     * Сложность: O(n²), где n - размер массива
     *
     * Обоснование сложности:
     * - Внешний цикл выполняется n раз (от i=0 до n-1)
     * - Внутренний цикл для каждого i выполняется (n-i-1) раз
     * - Общее количество итераций: Σ(i=0 to n-1)(n-i-1) = (n-1) + (n-2) + ... + 1 + 0
     * - Эта сумма равна n(n-1)/2
     * - В асимптотике: O(n(n-1)/2) = O(n²)
     */
    private static ArrayList<String> findAllInversions(int[] dataArray) {
        ArrayList<String> inversionList = new ArrayList<>();
        int arrayLength = dataArray.length;

        // Перебираем все возможные пары элементов
        for (int firstIndex = 0; firstIndex < arrayLength; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < arrayLength; secondIndex++) {
                // Проверяем условие инверсии
                if (dataArray[firstIndex] > dataArray[secondIndex]) {
                    String inversionInfo = String.format("Инверсия: позиции (%d, %d), значения [%d > %d]",
                            firstIndex, secondIndex, dataArray[firstIndex], dataArray[secondIndex]);
                    inversionList.add(inversionInfo);
                }
            }
        }

        return inversionList;
    }

    private static void displayResults(ArrayList<String> inversions) {
        int totalInversions = inversions.size();

        System.out.println("\nРезультаты анализов инверсий");
        System.out.println("Общее количество инверсий: " + totalInversions);

        if (totalInversions > 0) {
            System.out.println("\nСписок всех инверсий:");
            for (String inversion : inversions) {
                System.out.println(inversion);
            }
        } else {
            System.out.println("Инверсий в массиве не обнаружено.");
        }
    }

    /**
     * Главный метод программы
     * Общая сложность алгоритма: O(n²)
     *
     * Составляющие сложности:
     * 1. readArrayFromInput(): O(n)
     * 2. findAllInversions(): O(n²) - доминирующая операция
     * 3. displayResults(): O(k), где k ≤ n(n-1)/2
     *
     * Итог: O(n) + O(n²) + O(n²) = O(n²)
     */
    public static void main(String[] args) {
        int[] userArray = readArrayFromInput();
        ArrayList<String> foundInversions = findAllInversions(userArray);
        displayResults(foundInversions);
    }
}

/*
ДОПОЛНИТЕЛЬНЫЙ АНАЛИЗ СЛОЖНОСТИ:

1. ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ:
   - O(n) для хранения исходного массива
   - O(k) для хранения списка инверсий, где k - количество инверсий
   - В худшем случае (массив отсортирован по убыванию) k = n(n-1)/2, поэтому O(n²)
   - Общая пространственная сложность: O(n²) в худшем случае

2. ХАРАКТЕРНЫЕ СЛУЧАИ:

   ХУДШИЙ СЛУЧАЙ (массив отсортирован по убыванию):
   - Пример: [5,4,3,2,1]
   - Количество инверсий: n(n-1)/2
   - Время выполнения: O(n²)
   - Память: O(n²)

   ЛУЧШИЙ СЛУЧАЙ (массив отсортирован по возрастанию):
   - Пример: [1,2,3,4,5]
   - Количество инверсий: 0
   - Время выполнения: O(n²) - все равно проверяем все пары
   - Память: O(1) - не храним инверсии

   СРЕДНИЙ СЛУЧАЙ (случайный массив):
   - Ожидаемое количество инверсий: n(n-1)/4
   - Время выполнения: O(n²)
   - Память: O(n²)

*/
