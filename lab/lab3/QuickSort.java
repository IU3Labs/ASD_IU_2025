package lab3;

import java.util.List;

public class QuickSort {
    public static void sort(List<int[]> list) {
        //случай когда список уже отсортирован
        if (list.size() <= 1) return;

        //точка входа в рекурсивный алгоритм сортировки
        //low и high - границы сортировки
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<int[]> list, int low, int high) {
        if (low < high) {
            //получение индекса опорного элемента
            int propIndex = separation(list, low, high);

            // Рекурсивно сортируем обе части
            quickSort(list, low, propIndex - 1);
            quickSort(list, propIndex + 1, high);
        }
    }

    private static int separation(List<int[]> list, int low, int high) {
        //опорный элемент, берем за последний
        int prop = list.get(high)[1];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            //сортируем так, чтобы элементы больше опорного оказались справа от него,
            //а элементы меньше опорного - слева
            if (list.get(j)[1] >= prop) {
                i++;
                swap(list, i, j);
            }
        }

        //ставим на нужное место опорный элемент
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<int[]> list, int i, int j) {
        int[] template = list.get(i);
        list.set(i, list.get(j));
        list.set(j, template);
    }
}

//Сложность алгоритма
//Пусть n - количество элементов в массиве
//k - количество элементов в подмассиве
//В процессе сортировки массив делится на 2 части
//В функции separation есть цикл for - сложность O(n)
//Тогда для левой части время t зависит от k t(k)
//для правой части t(n-k-1)
//Общее t(n) = t(k) + t(n-k-1) + o(n)

//мы считаем для общего случая, когда
//массив в среднем делиться +- пополаи,
//то есть k = n/2, тогда
//t(n) = 2t(n/2) + O(n)
//рассмотрим различные уровни рекурсии
//1) t(n) = n + 2t(n/2)
//2) t(n) = n + 2(n/2 + 2t(n/4)) = 2n + 4t(n/4)
//3) t(n) = n + 2(n/2 + 2(n/4 + 2t(n/8))) = 3n + 8t(n/8)
//............
// t(n) = k*n + 2^k * (n/(2^k)) (*)
//рекурсия продолжается пока n/(2^k) = 1
//2^k = n
//k = log2(n)
//подставляем в (*):
//t(n) = n*log2(n) + 2^(log2(n)) * t(1) =
// = n*log2(n) * n * t(1)
//n*log(n) растет быстрее n, поэтому
//t(n) = o(n*log(n))