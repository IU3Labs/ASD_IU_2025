Докажем, что временная сложность равна **n*log(n)**

    static int[] findMostFrequentNumbers(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        ArrayList<Pair> frequencies = countFrequencies(nums);
        quickSortByFrequency(frequencies, 0, frequencies.size() - 1);

        int[] mostFrequentNumbers = new int[k];
        for (int i = 0; i < k; i++) {
            mostFrequentNumbers[i] = frequencies.get(frequencies.size() - i - 1).number;
        }
        return mostFrequentNumbers;
    }

Начнём с простого, с функции *countFrequencies*

    static ArrayList<Pair> countFrequencies(int[] sortedList) {
        ArrayList<Pair> frequencies = new ArrayList<>();
        int currentFrequency = 1;
        for (int i = 0; i < sortedList.length; i++) {
            if ((i < sortedList.length - 1 && sortedList[i] == sortedList[i + 1])) {
                currentFrequency++;
            } else {
                frequencies.add(new Pair(sortedList[i], currentFrequency));
                currentFrequency = 1;
            }
        }
        return frequencies;
    }

Функция *countFrequencies* прогоняется по списку один раз, значит сложность линейная, т.е. **O(n)**

В конце функции *findMostFrequentNumbers* когда берём **k** элементов, тогда получаем тоже линейную зависимость, но уже **O(k)**, где k < n

Итого осталось найти временную сложность у *quickSort*

    static void quickSort(int[] list, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int pivot = list[left + (right - left) / 2];
        while (i <= j) {
            while (list[i] < pivot) i++;
            while (list[j] > pivot) j--;
            if (i <= j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(list, left, j);
        quickSort(list, i, right);
    }

Функция разделяет массив на подмассивы и имеет рекурсивную реализацию, поэтому время на её выполнение можно записать как **T(n) = T(r) + T(n-r-1) + O(n)**, где:
- **T(r)** - время на выполнение quickSort для левой части
- **Т(n-r-1)** - время на выполнение quickSort для правой части
- **O(n)** - линейная зависимость для выполнения цикла while

В основном части будут делиться примерно пополам, а значит можно записать выражение следующим образом:

**T(n)  = T(r) + T(n-r-1) + O(n) = T(n/2) + T(n/2) + O(n) = 2T(n/2) + O(n)**

Глубина рекурсии примерно равна log₂n, так как каждый раз уменьшаем размер примерно в 2 раза. Следовательно:

$$
T(n) = \sum_{i=0}^{log₂n}T_i 
$$

где T<sub>i</sub> - время на выполнение операция на каждом шаге рекурсии, имеет линейную сложность. Соответсвенно общая временная сложность *quickSort* будет пропорциональна **n*log(n)**.

Функция *quickSortByFrequency* будет иметь сложность пропорциональную **m*log(m)**, где m < n

-----------------------------------------------------------------------------------------------------------

Итого получаем сложность **O(n) + O(k) + O(nlog(n)) + O(mlog(m)) = O(nlog(n))**