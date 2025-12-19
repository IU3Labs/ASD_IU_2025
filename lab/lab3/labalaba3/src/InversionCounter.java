/* Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
данном массиве и вывести их. Дать комментарии. Вычислить сложность.*/

public class InversionCounter {

    public static int findInversions(int[] arr) {
        if (arr == null || arr.length == 0) { //если массив пустой завершаем работу
            System.out.println("Массив пуст.");
            return 0;
        }

        int n = arr.length;
        int count = 0; //счетчик инверсий
        System.out.println("Найденные инверсии (i, j):");

        //перебираем все пары (i, j), где i < j
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    System.out.println("(" + i + ", " + j + ")");
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = Additionals.inputArray();
        int totalInversions = findInversions(arr);

        System.out.println("Общее количество инверсий: " + totalInversions);

}
}

//временная сложность: O(N^2)
//пространственная: O(1) (дополнительная память не используется для доп структур данных
// все инверсии выводятся сразу, используются только несколько переменных
// n, count, i, j — константное количество. соответственно, сложность тоже константная)
