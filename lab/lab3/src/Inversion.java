//Дан массив аг из N лементов.
//Назовем инверсией пару индексов (і, j), таких что і < j и arr[i] > arr[].
//Требуется определить количество инверсий в данном массиве и вывести их.
// Дать комментарии.
// Вычислить сложность.

import java.util.ArrayList;
import java.util.Arrays;

public class Inversion {
    //Константы для удобства тестирования
    static final int LENGTH = 7;
    static final int[] ARRAY = {1, 5, 2, 8, 9, 3, 4};

    public static void main(String[] args) {
        System.out.println("Количество инверсий в массиве:");
        for (int i = 0; i < LENGTH; i++) {
            System.out.println(ARRAY[i]);
        }

        ArrayList<int[]> inversions =  GetInversions(ARRAY);
        int inversionsNumber = inversions.size();

        System.out.println("равно " + inversionsNumber);
        for (int i = 0; i < inversionsNumber; i++) {
            System.out.println(Arrays.toString(inversions.get(i)));
        }
    }

    //Метод, возвращающиий инверсии в данном массиве
    static ArrayList<int[]> GetInversions(int[] arr) {
        ArrayList<int[]> inversions = new ArrayList<>();
        int len = arr.length;

        //Перебираем все элементы, проверяя на условие инверсии
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int[] inversion = {i, j};
                    //Заносим пару индексов инверсии в список
                    inversions.add(inversion);
                }
            }
        }

        return inversions;
    }
}

//Сложность алгоритма
//Пусть n - количество элементов в перебираемом массиве
//Внешний цикл выполняется n раз от 0 до n-1
//Количество итераций внутреннего цикла зависит от i
//При i = o j изменяется от 1 до n-1 => n-1 раз
//При i = 1 j изменяется от 2 до n-1 => n-2 раз
//При i = 2 j изменяется от 3 до n-1 => n-3 раз
//...
//При i = n - 2 j изменяется от n - 2 до n - 1 => 1 раз
//При i = n - 1 j изменяется от n - 1 до n - 1 => 0 раз
//Общее количество итераций может быть подсчитано как
//(n-1) + (n-2) + (n-3) + ... + 1 + 0 = n*(n-1)/2
//n(n-1)/2 = 1/2 * n^2 - 1/2 * n
//n^2 растет быстрее остальных => сложность алгоритма будет O(n^2)
//Ответ: O(n^2)