public class ArrayListResizeDemo {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
    }
}

class MyArrayList<T> {

    private Object[] data;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[0];
    }

    public void add(T value) {
        if (size == data.length) {
            resize();
        }

        data[size++] = value;

        // “условный load factor”: насколько заполнен массив после добавления
        double loadFactor = (data.length == 0) ? 0 : (double) size / data.length;

        System.out.printf(
                "add(%d): size=%d, capacity=%d, load=%.2f%n",
                value, size, data.length, loadFactor
        );
    }

    private void resize() {
        int oldCapacity = data.length;
        int newCapacity;

        if (oldCapacity == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = oldCapacity + (oldCapacity >> 1); // рост 1.5x
        }

        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, oldCapacity);
        data = newData;

        System.out.printf(
                "resize(): capacity %d → %d%n",
                oldCapacity, newCapacity
        );
    }
}
