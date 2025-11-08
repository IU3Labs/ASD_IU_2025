package lab2.b;

public class QueueOfQueue {
    private int start; // Указатель на первый элемент очереди
    private int end;   // Указатель на позицию для следующего добавления
    private final int[] elements;
    private int count; // Текущее количество элементов

    public QueueOfQueue(int capacity) {
        count = 0;
        start = 0;
        end = 0;
        elements = new int[capacity];
    }

    public int remove() {
        if (count != 0) {
            int value = elements[start];

            // Сдвигаем указатель начала вперед по кругу
            start = (start + 1) % elements.length;
            count--;
            return value;
        } else return Integer.MIN_VALUE; // очередь пуста
    }

    public boolean add(int value) {
        elements[end] = value;
        // Перемещаем указатель конца по кругу
        end = (end + 1) % elements.length;
        if (count == elements.length) {
            // Если очередь полна, сдвигаем и начало
            start = (start + 1) % elements.length;
        } else count++;
        return true;
    }

    public int size() {
        return count;
    }

    public void print() {
        if (count == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < count - 1; i++) {
                // Обходим элементы от начала
                int index = (start + i) % elements.length;
                System.out.print(elements[index] + ", ");
            }
            System.out.print(elements[(start + count - 1) % elements.length]);
            System.out.println("]");
        }
    }

    public void printReversed() {
        if (count == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < count; i++) {
                // Обходим элементы с конца
                int index = (end - 1 - i + elements.length) % elements.length;
                System.out.print(elements[index]);
                if (i < count - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}
