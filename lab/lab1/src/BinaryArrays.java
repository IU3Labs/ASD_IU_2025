/*  Группа Б
    Дан массив целых чисел, представляющий двоичное число.
    Пример, дан массив bi_arr = [1, 1, 0]. Этот массив в десятичной системе
    выглядит так: arr = [1, 3, 6]. То есть:
    • arr[0] = bi_arr[0] = 1! = 1"#,
    • arr[1] = bi_arr[0] bi_arr[1] =11! = 3"#,
    • arr[2] = bi_arr[0] bi_arr[1] bi_arr[2] =110! = 6"#
    Так же дано целое положительное число – n. Вернуть массив Boolean, где
    true – число делится на N, false – нет.
    Пусть n = 6, тогда для предыдущего примера результат должен выглядеть
    так: [false, false, true].
    Примечание. Делитель тоже необходимо ввести с клавиатуры.
 */



public class BinaryArrays {
    public static void main(String[] args) {
        int[] inputArray = InpOutMethods.getArray();

        int del = InpOutMethods.getInt("Введите делитель");
        boolean[] booleansArray = binaryToBoolean(inputArray, del);

        InpOutMethods.printBoolArray(booleansArray);
    }

    private static boolean[] binaryToBoolean(int[] binaryArray, int del) {
        boolean[] booleanArray = new boolean[binaryArray.length];
        int converse = 0;

        for (int i = 0; i < binaryArray.length; i++) {
            converse = converse * 2 + binaryArray[i];
            booleanArray[i] = (converse % del == 0);
        }
        return booleanArray;
    }
}