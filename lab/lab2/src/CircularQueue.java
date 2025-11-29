public class CircularQueue {
    private int[] array;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean enqueue(int value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) return null;
        int result = array[head];
        head = (head + 1) % capacity;
        size--;
        return result;
    }

    public Integer peek() {
        if (isEmpty()) return null;
        return array[head];
    }

    //реализация quickSort

    private int getByLogicalIndex(int i) {
        return array[(head + i) % capacity];
    }

    private void setByLogicalIndex(int i, int value) {
        array[(head + i) % capacity] = value;
    }

    private void swapByLogicalIndex(int i, int j) {
        int temp = getByLogicalIndex(i);
        setByLogicalIndex(i, getByLogicalIndex(j));
        setByLogicalIndex(j, temp);
    }

    private int partition(int left, int right) {
        int pivot = getByLogicalIndex(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (getByLogicalIndex(j) <= pivot) {
                i++;
                swapByLogicalIndex(i, j);
            }
        }
        swapByLogicalIndex(i + 1, right);
        return i + 1;
    }

    private void quickSort(int left, int right) {
        if (left < right) {
            int pi = partition(left, right);
            quickSort(left, pi - 1);
            quickSort(pi + 1, right);
        }
    }

    public void sort() {
        if (size > 1) {
            quickSort(0, size - 1);
        }
    }

    public void printQueue() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            int phys = (head + i) % capacity;
            System.out.print(array[phys]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    //Реализация mergeSort

    public void mergeSort() {
        if (size > 1) {
            int[] temp = new int[size];
            mergeSortRec(0, size - 1, temp);
        }
    }

    private void mergeSortRec(int left, int right, int[] temp) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSortRec(left, mid, temp);
        mergeSortRec(mid + 1, right, temp);
        merge(left, mid, right, temp);
    }

    private void merge(int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (getByLogicalIndex(i) <= getByLogicalIndex(j)) {
                temp[k++] = getByLogicalIndex(i++);
            } else {
                temp[k++] = getByLogicalIndex(j++);
            }
        }

        while (i <= mid) {
            temp[k++] = getByLogicalIndex(i++);
        }

        while (j <= right) {
            temp[k++] = getByLogicalIndex(j++);
        }

        for (int t = 0; t < k; t++) {
            setByLogicalIndex(left + t, temp[t]);
        }
    }

    public void sortMerge() {
        mergeSort();
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(5);

        q.enqueue(4);
        q.enqueue(1);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(2);

        System.out.print("Исходная очередь: ");
        q.printQueue();

        q.sort();
        System.out.print("После sort():    ");
        q.printQueue();

        q.dequeue();
        q.dequeue();
        System.out.print("После двух dequeue(): ");
        q.printQueue();

        q.enqueue(7);
        q.enqueue(0);
        System.out.print("После enqueue(7), enqueue(0): ");
        q.printQueue();

        q.sort();
        System.out.print("После второго sort(): ");
        q.printQueue();

//        System.out.print("Перед mergeSort(): ");
//        q.printQueue();
//        q.mergeSort();
//        System.out.print("После mergeSort(): ");
//        q.printQueue();

    }
}
