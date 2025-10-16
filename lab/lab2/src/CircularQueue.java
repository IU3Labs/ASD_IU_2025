//2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
//логику.

//Циклическая очередь - при выходе за предел вместимости начинает перезаписывать себя с "начала"
public class CircularQueue<T> {

    private T[] queue;          // Массив для хранения элементов
    private int start;          // Указатель на первый элемент
    private int end;           // Указатель на последний элемент
    private int size;           // Текущее количество элементов
    private int capacity;       // Максимальная емкость

    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Емкость должна быть положительной");
        }

        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.start = 0;
        this.end = -1;  // -1 означает, что очередь пуста
        this.size = 0;
    }

    public void addEnd(T element) {
        if (isFull()) {
            System.out.println("Очередь переполнена,перезаписываем");
            start = (start + 1) % capacity;//Сдвигаем start
        }else {
            size++;
        }

        end = (end + 1) % capacity;// Перемещаем end на следующую позицию (с учетом цикличности)
        queue[end] = element;

        if (size == 1) {// Если это первый элемент, устанавливаем start на него
            start = end;
        }
    }

    public T deleteStart() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }

        T element = queue[start];
        queue[start] = null;  // Помогаем сборщику мусора

        start = (start + 1) % capacity;// Перемещаем start на следующую позицию (с учетом цикличности)
        size--;

        // Если очередь стала пустой, сбрасываем указатели
        if (isEmpty()) {
            end = -1;
            start = 0;
        }

        return element;
    }

    public T checkFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return queue[start];
    }

    public T checkEnd() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }
        return queue[end];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    public void clearQueue() {
        // Обнуляем массив для помощи сборщику мусора
        for (int i = 0; i < capacity; i++) {
            queue[i] = null;
        }
        this.start = 0;
        this.end = -1;
        this.size = 0;
    }

    public void printQueue() {
        System.out.println("Состояние: " + (isEmpty() ? "Пустая" :
                (isFull() ? "Заполнена" : "Частично заполнена")));
        System.out.println("Заполнение: " + size + "/" + capacity);
        System.out.println("Start: " + start + ", End: " + end);

        System.out.print("Массив: [");
        for (int i = 0; i < capacity; i++) {
            if (queue[i] != null) {
                System.out.print(queue[i]);
            } else {
                System.out.print("null");
            }
            if (i < capacity - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        if (!isEmpty()) {
            System.out.println("Первый элемент: " + checkFirst());
            System.out.println("Последний элемент: " + checkEnd());

            System.out.print("Логический порядок: [");// Показать логический порядок элементов
            int current = start;
            for (int i = 0; i < size; i++) {
                System.out.print(queue[current]);
                if (i < size - 1) {
                    System.out.print(", ");
                }
                current = (current + 1) % capacity;
            }
            System.out.println("]");
        }
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        int current = start;
        for (int i = 0; i < size; i++) {
            result[i] = queue[current];
            current = (current + 1) % capacity;
        }
        return result;
    }

    public static void main(String[] args){
        CircularQueue<Integer> queue = new CircularQueue<>(10);

        for(int i = 1; i<=queue.capacity;i++){
            queue.addEnd(i);
            queue.printQueue();
            System.out.println();
        }
        queue.deleteStart();
        for(int i = 1; i<=3;i++){
            queue.addEnd(10+i);
            queue.printQueue();
            System.out.println();
        }

        System.out.println("Очищаем");
        queue.clearQueue();
        queue.printQueue();
    }
}
