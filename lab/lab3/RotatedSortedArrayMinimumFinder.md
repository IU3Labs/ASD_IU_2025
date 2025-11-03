Докажем, что временная сложность равна **log(n)**

    static int findMinimum(int[] array) {
        int left = 0;
        int right = array.length - 1;
        if (array[left] < array[right]) return array[left];
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (array[mid] > array[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[right];
    }

- Количество элементов перед поиском - **n**
- После первой итерации уже **n/2**, после воторой **n/4**, после третьей **n/8** и т.д.
- После i-ой итерации будет **n/2<sup>i</sup>** элементов.
- На последней итерации будет **1** элемент.
- Значит **1 = n/2<sup>i</sup>** 

Следовательно **n = 2<sup>i</sup>** или же **i = log(n)**, где n - число элементов в массиве. Значит временная сложность **O(log(n))**.