// 1 Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
// таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий в
// данном массиве и вывести их. Дать комментарии. Вычислить сложность.


// В данном задании используется только алгоритм MergeSort, его сложность n*log(n), следовательно сложность n*log(n)

import java.util.ArrayList;
import java.util.Scanner;

public class Inversions {
    public static void main() { 
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> nums = Utils.createArray(scanner);

        int[] numsList = toIntList(nums);

        Utils.zeroInvers();
        Utils.mergeSort(numsList);
        System.out.println("Inversions count: " + Utils.inversions);
    }

    public static int[] toIntList(ArrayList<Integer> nums) {
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }
}
