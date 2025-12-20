import java.util.Arrays;

public class A4 {
    /*Дан массив неотрицательных чисел arr и битовая маска mask.
Отсортируйте массив по возрастанию значений arr[i] & mask. При
равенстве отсортируйте числа по возрастанию. Сложность: O(N log N).
Докажите сложность.*/

    public static void main(String[] args) {
        int[] array = {10, 7, 3, 12, 5, 8};
        int mask = 6;

        sortByMaskedValue(array, mask);

        System.out.println(Arrays.toString(array));
    }

    public static void sortByMaskedValue(int[] array, int mask) {
        Integer[] boxedArray = boxArray(array);

        Arrays.sort(boxedArray, (firstValue, secondValue) ->
        {
            int firstMaskedValue = firstValue & mask;
            int secondMaskedValue = secondValue & mask;

            if (firstMaskedValue != secondMaskedValue) {
                return Integer.compare(firstMaskedValue, secondMaskedValue);
            }

            return Integer.compare(firstValue, secondValue);
        });

        unboxArray(boxedArray, array);
    }

    private static Integer[] boxArray(int[] array) {
        Integer[] boxedArray = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            boxedArray[i] = array[i];
        }

        return boxedArray;
    }

    private static void unboxArray(Integer[] boxedArray, int[] array) {
        for (int i = 0; i < boxedArray.length; i++) {
            array[i] = boxedArray[i];
        }
    }
}
