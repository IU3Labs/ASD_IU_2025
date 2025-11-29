import java.util.*;

//Дан массив из N целых чисел (N > 10^7), который может содержать дубликаты. Нфйдите все уникальные элементы и выведите их в отсортированном порядке. Сложность O(N log U). Докажите сожность.
public class GroupATask3 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 2, 4, 1, 5, 3, 6};
        int[] result = findUniqueSorted(arr);
        ArrayUtility.printArray(result);
    }

    // O(N + U log U) ≈ O(N log U) при U ≤ N
    public static int[] findUniqueSorted(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        Arrays.sort(result);
        return result;
    }
}
//Доказательство сложности: Добавление N элементов в HashSet - O(N). Конвертация в массив - O(U). Сортировка - O(U log U). Итого O(N + U log U), что при U ≤ N эквивалентно O(N log U).