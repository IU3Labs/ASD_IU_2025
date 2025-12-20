import java.util.*;


//1 Дан целочисленный массив nums и целое число k, верните k наиболее
//часто встречающихся элементов. Вернуть ответ в любом порядке.
//Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.

public class TopKFrequentС1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размер массива:");
        int size = scanner.nextInt();

        int[] nums = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("Введите k:");
        int k = scanner.nextInt();

        int[] result = topKFrequent(nums, k);
        System.out.println("Вывод: " + Arrays.toString(result));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // Сортировка массива O(n log n)
        Arrays.sort(nums);

        // Подсчет частот и сохранение в список - O(n)
        List<int[]> freqList = new ArrayList<>();

        int current = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == current) {
                count++;
            } else {
                freqList.add(new int[]{current, count});
                current = nums[i];
                count = 1;
            }
        }
        //Добавляем последний элемент
        freqList.add(new int[]{current, count});

        // cортировка по частоте (убывание) O(m log m)
        freqList.sort((a, b) -> b[1] - a[1]);

        //Выбор k наиболее частых элементов O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = freqList.get(i)[0];
        }

        return result;
    }
}