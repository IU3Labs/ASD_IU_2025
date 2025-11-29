//Группа Б.
//Пусть любое число – это массив его цифр слева направо. Пример, число
//1234 – это массив [1,2,3,4].
//Дан массив целых чисел. Реализовать умножение двух чисел.
//Пример, [1, 2, 3, 4] * [1, 1] = [1, 3, 5, 7, 4].
//Результат – число, представленное массивом.

public class MultiplyArrays {
    public static void main(String[] args){
        int[] array1 = ArrayMethods.newArray();
        int[] array2 = ArrayMethods.newArray();

        ArrayMethods.printArray(multiply(array1, array2));
    }
    private static int[] multiply(int[] array1, int[] array2){
        return(array(number(array1) * number(array2)));
    }

    private static int number(int[] array){
        int number = 0;
        for(int i = 0; i < array.length; i++){
            number += array[i] * Math.pow(10, array.length - i - 1);
        }
        return number;
    }

    private static int[] array(int number){
        int length = String.valueOf(number).length();
        int[] array = new int[length];
        for(int i = length - 1; i >= 0; i--){
            array[i] = number % 10;
            number /= 10;
        }
        return array;
    }
}
