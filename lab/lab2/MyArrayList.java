public class MyArrayList<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 5000000;

    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public void addLast(T element) {
        if (size == data.length) {
            ensureCapacity();
        }
        data[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == data.length) {
            ensureCapacity();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    public void addMiddle(T element) {
        int middle = size / 2;
        add(middle, element);
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null;
        return removedElement;
    }

    private void ensureCapacity() {
        int newCapacity = data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public void printArrayList() {
        for (T element: data) {
            System.out.println(element);
        }
    }

    public void printArrayListReversed() {
        int sizeArray = size();
        for (int i = sizeArray - 1; i >= 0; i--) {
            System.out.println(data[i]);
        }
    }
}
