/* Методы для работы с массивами */


public class ArrayMethods {


    public static int[] bubleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int k = 0; k < array.length - 1 - i; k++) {
                if (array[k] > array[k + 1]) {
                    int temp = array[k];
                    array[k] = array[k + 1];
                    array[k + 1] = temp;
                }
            }
        }
        return array;
    }
}