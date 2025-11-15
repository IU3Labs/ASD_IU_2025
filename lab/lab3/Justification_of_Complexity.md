# **Обоснование сложности алгоритма в задаче B(3б) № 1**

    public static int[] findMostFrequentNumbers(int[] nums, int k)

**1.** Подсчет частот: _O(n)_
- Проходим по всем n элементам массива
- Операции put и get в HashMap работают за _O(1)_ в среднем случае

`frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);`
     
**2.** Создание списка: _O(m)_, где _m_ - количество уникальных элементов
- В худшем случае m = n (все элементы уникальны)

`List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());`
  
**3.** Сортировка: _O(m log m)_
- В худшем случае _m = n_, поэтому _O(n log n)_

`entries.sort((a, b) -> b.getValue() - a.getValue());`

_**Замечание:**_ - Официальное описание метода sort интерфейса List говорит о том, что эта реализация сортировки требует в разы меньше, чем n log n шагов (Выделил, где об этом говорится)

java.util.List<E>

@Contract(mutates = "this")  
public void sort(     java.util.Comparator<? super E> c )


Sorts this list according to the order induced by the specified Comparator. The sort is stable: this method must not reorder equal elements.
All elements in this list must be mutually comparable using the specified comparator (that is, c.compare(e1, e2) must not throw a ClassCastException for any elements e1 and e2 in the list).
If the specified comparator is null then all elements in this list must implement the Comparable interface and the elements' natural ordering should be used.
This list must be modifiable, but need not be resizable.

Params:

c – the Comparator used to compare list elements. A null value indicates that the elements' natural ordering should be used
Throws:

ClassCastException – if the list contains elements that are not mutually comparable using the specified comparator
UnsupportedOperationException – if the list's list-iterator does not support the set operation
IllegalArgumentException – (optional) if the comparator is found to violate the Comparator contract

Implementation Requirements:

The default implementation obtains an array containing all elements in this list, sorts the array, and iterates over this list resetting each element from the corresponding position in the array. This avoids the n^2 log(n) performance that would result from attempting to sort a linked list in place.)

Implementation Note:

[**This implementation is a stable, adaptive, iterative mergesort that requires far fewer than n lg(n)**]() comparisons when the input array is partially sorted, while offering the performance of a traditional mergesort when the input array is randomly ordered. [**If the input array is nearly sorted, the implementation requires approximately n comparisons**](). Temporary storage requirements vary from a small constant for nearly sorted input arrays to n/2 object references for randomly ordered input arrays.
The implementation takes equal advantage of ascending and descending order in its input array, and can take advantage of ascending and descending order in different parts of the same input array. It is well-suited to merging two or more sorted arrays: simply concatenate the arrays and sort the resulting array.
The implementation was adapted from Tim Peters's list sort for Python ( TimSort ). It uses techniques from Peter McIlroy's "Optimistic Sorting and Information Theoretic Complexity", in Proceedings of the Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474, January 1993.
Since:
1.8
< 19 >
  
**4.** Выбор k элементов: _O(k) = O(n)_ в худшем случае

`int[] result = new int[k];

    for (int i = 0; i < k; i++) {
        result[i] = entries.get(i).getKey();
    }`
**Итог:** _O(n) + O(n) + O(n log n) + O(n) = O(n log n)_


# **Обоснование сложности алгоритма в задаче A № 1**

    public static int findMinimumInRotatedSortedArray(int[] rsa)

Решение задачи основано на применении алгоритма бинарного поиска.

**1.** Корнер кейс

Если массив ни разу не повёрнут, то значение на индексе left есть искомый элемент

        int left = 0, right = rsa.length - 1;
        if (rsa[left] < rsa[right]) return rsa[left];

**2.** Количество элементов, которые необходимо рассмотреть, перед выполнением поиска = _n_
После первой итерации = _n / 2_, После второй = _n / 4_. Итого после _i-ой_ итерации = _n / (2^i)_, а на последнем проходе остаётся рассмотреть _1_ элемент.

**3.** В итоге получаем формулу: _1 = n / (2^i)_ или _n = 2^i_

**4.** Это равносильно записи _i = log(n)_, где _n_ - размер массива, а _i_ - итоговое количество шагов