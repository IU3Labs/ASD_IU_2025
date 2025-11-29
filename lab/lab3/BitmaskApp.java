import java.util.Arrays;
/*4 Дан массив неотрицательных чисел arr и битовая маска mask.
Отсортируйте массив по возрастанию значений arr[i] & mask. При
равенстве отсортируйте числа по возрастанию. Сложность: O(N log N).
Докажите сложность.*/
public class BitmaskApp {
//не по маске b dsdjl
    public static void main(String[] args) {
        int[] arr = {7, 3, 12, 5};
        Integer mask = 3;

        sortByMask(arr, mask);

        System.out.println("Отсортированный массив:");
        for (int value : arr) {
            System.out.println(value);
        }
    }

    private static void sortByMask(int[] arr, int mask) {
        Integer[] boxedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);//массив,поток,массив
        // Сортируем массив с помощью Arrays.sort
        Arrays.sort(boxedArr, (a, b) -> {
            // Применяем битовую маску к элементам
            int maskedA = a & mask;
            int maskedB = b & mask;

            // Если значения после маски равны, сравниваем сами элементы
            if (maskedA == maskedB) {
                return Integer.compare(a, b);
            }

            // Иначе сравниваем по значению после маски
            return Integer.compare(maskedA, maskedB);

        });
        for (int i=0;i<arr.length;i++) {
            arr[i] = boxedArr[i];
        }
        // В среднем и в худшем случае: O(N log N)
        // N — количество элементов в массиве
        // Каждое сравнение выполняется за O(1) — операция & и сравнение чисел
    }
}

/*
Доказательство сложности:

1. Сортировка массива в Java использует TimSort (гибрид быстрой сортировки и сортировки слиянием).
2. TimSort имеет сложность O(N log N) в среднем и худшем случае.
3. Каждый вызов компаратора выполняется за O(1), т.к. битовая маска и сравнение чисел — константа.
4. Следовательно, общая сложность сортировки: O(N log N).

Пространственная сложность: O(N) дополнительной памяти в худшем случае (TimSort требует временный массив).
*/