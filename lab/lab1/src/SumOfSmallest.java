/*  Группа Б
    Дан массив целых чисел. Минимальное количество элементов – 5 Вернуть
    число, которое является суммой двух наименьших положительных чисел.
*/


public class SumOfSmallest {
    public static void main(String[] args) {
        int[] inputArray = InpOutMethods.getArray();

        inputArray = ArrayMethods.bubleSort(inputArray);

        int target = searching(inputArray);

        System.out.println(target);
    }

    private static int searching(int[] array) {
        int smallest = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (smallest == -1) smallest = array[i];
                else return smallest + array[i];
            }
        }
        return -1;
    }
}