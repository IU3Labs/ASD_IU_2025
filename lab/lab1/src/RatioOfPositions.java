/*  Группа Б
    Дан целочисленный массив. Верните число, частота встречи которого в
    массиве равна его значению. Если таких чисел нет, вернуть «-1». Если
    таких чисел несколько, вернуть наибольшее.
*/

public class RatioOfPositions {
    public static void main(String[] args) {
        int[] inputArray = InpOutMethods.getArray();

        inputArray = ArrayMethods.bubleSort(inputArray);

        int target = ratio(inputArray);

        System.out.println(target);
    }

    public static int ratio(int[] array) {
        int reference = 1, count = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == reference) {
                count += 1;
            } else {
                if (reference == count) return reference;
                reference = array[i];
                count = 1;
            }
        }
        return -1;
    }
}