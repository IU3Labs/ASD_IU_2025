package lab3;

import static lab3.TaskA1.readArray;

public class TaskC2 {

    /**
     * Вам предоставлен целочисленный массив nums, отсортированный
     * в порядке неубывания. Определите, возможно ли разбить nums
     * на одну или несколько подпоследовательностей таким образом,
     * чтобы выполнялись оба следующих условия:
     * <p>
     * 1. Каждая подпоследовательность представляет собой
     * последовательную возрастающую последовательность
     * (т.е. каждое целое число ровно на единицу больше предыдущего целого числа).
     * 2. Все подпоследовательности имеют длину 3 или более.
     * <p>
     * Верните значение true, если вы можете разделить числа
     * в соответствии с вышеуказанными условиями, или значение false
     * в противном случае. Также необходимо вывести последовательности.
     * <p>
     * Подпоследовательность массива – это новый массив, который
     * формируется из исходного массива путем удаления некоторых
     * (может не быть ни одного) элементов без изменения
     * относительного положения остальных элементов
     * (т.е. [1,3,5] является подпоследовательностью [1,2,3,4,5],
     * в то время как [1,3,2] не является).
     * <p>
     * Примечание. Сложность должна быть O(n). Докажите сложность.
     */

    public static void main(String[] args) {
        int[] nums = readArray();
        System.out.println(subdivide(nums));
    }

    private static boolean subdivide(int[] nums) {
        if (nums.length < 3) return false;

        StringBuilder subdivision = new StringBuilder();
        StringBuilder currentSequence = new StringBuilder(String.valueOf(nums[0]));
        int counter = 1;

        /*
         Совершается один обход массива.
         Вложенных циклов и рекурсивных вызовов нет,
         каждая итерация выполняется за константное время.
         Таким образом, сложность алгоритма - O(n)
        */
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                counter++;
                currentSequence.append(", ").append(nums[i]);
            } else {
                if (counter < 3) return false;
                counter = 1;
                subdivision.append("[").append(currentSequence).append("], ");
                currentSequence.setLength(0);
                currentSequence.append(nums[i]);
            }
        }

        if (counter < 3) return false;
        subdivision.append("[").append(currentSequence).append("]");

        System.out.println(subdivision);
        return true;
    }
}