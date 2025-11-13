//Дан целочисленный массив nums и целое число к,
// верните к наиболее часто встречающихся элементов.
// Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.


import java.util.*;

public class ElementsFrequence {
    static final int[] ARRAY = {3,2,7,1,9,3,3,3,3,2,9,9};
    static final int K = 3;

    public static void main(String[] args) {
        int[] elements = getElements(ARRAY, K);
        for (int element : elements) {
            System.out.println(element);
        }
    }

    //метод класса, возвращающий массив наиболее часто встресающихся k элементов
    public static int[] getElements(int[] nums, int k) {
        //преобразуем массив в мапу "число" : "частота"
        Map<Integer, Integer> frequences = new HashMap<>();
        for (int num : nums) {
            frequences.put(num, frequences.getOrDefault(num, 0) + 1);
        }
        //в данном случае перебор массива происходит одним циклом, сложность O(n)

        //преобразуем хэш мап в список пар вида
        //[число, частота] для дальнейшей сортировки
        List<int[]> pairs = new ArrayList<>();
        //перебор в одном цикле, O(n)
        for (Map.Entry<Integer, Integer> frequence : frequences.entrySet()) {
            pairs.add(new int[]{frequence.getKey(), frequence.getValue()});
        }

        //сортировка (сложность O(n*log(n)) - доказано в "QuickSort.java")
        QuickSort.sort(pairs);

        //добавляем в результирующий массив только
        //первые k частых элементов массива nums
        int[] result = new int[k];
        //1 цикл, o(n)
        for (int i = 0; i < k; i++) {
            result[i] = pairs.get(i)[0];
        }

        return result;
    }
}

//сложность алгоритма
//o(n) + o(n) + o(n*log(n)) + o(n) = o(n*log(n)),
// так как n*log(n) растет быстрее n