/**
 * Многослойная очередь (очередь очередей) с приоритетным режимом обработки
 * Реализована с нуля без использования стандартных коллекций Java
 */
public class MultiLayerQueue<T> {

    /**
     * Внутренний класс для узла очереди
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Внутренний класс для реализации очереди
     */
    private static class Queue<T> {
        private Node<T> front; // начало очереди
        private Node<T> rear;  // конец очереди
        private int size;      // размер очереди

        Queue() {
            this.front = null;
            this.rear = null;
            this.size = 0;
        }

        /**
         * Добавление элемента в конец очереди
         */
        void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (rear == null) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        /**
         * Удаление элемента из начала очереди
         */
        T dequeue() {
            if (isEmpty()) {
                return null;
            }
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            return data;
        }

        /**
         * Просмотр элемента в начале очереди
         */
        T peek() {
            return isEmpty() ? null : front.data;
        }

        boolean isEmpty() {
            return front == null;
        }

        int size() {
            return size;
        }
    }

    private Queue<T>[] queues;        // массив очередей разных приоритетов
    private final int levels;               // количество уровней приоритета
    private int currentPriority;      // текущий активный приоритет
    private int totalSize;            // общее количество элементов

    /**
     * Конструктор создает многослойную очередь с указанным количеством уровней
     */
    @SuppressWarnings("unchecked")
    public MultiLayerQueue(int levels) {
        if (levels <= 0) {
            throw new IllegalArgumentException("Количество уровней должно быть положительным");
        }

        this.levels = levels;
        this.queues = new Queue[levels];
        for (int i = 0; i < levels; i++) {
            queues[i] = new Queue<>();
        }
        this.currentPriority = 0;
        this.totalSize = 0;
    }

    /**
     * Добавление элемента в очередь указанного приоритета
     */
    public void enqueue(T element, int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Неверный приоритет: " + priority);
        }
        queues[priority].enqueue(element);
        totalSize++;
    }

    /**
     * Извлечение элемента по приоритетному алгоритму
     * Сначала обрабатываются элементы высшего приоритета
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        // Поиск очереди с наивысшим приоритетом, содержащей элементы
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) {
                currentPriority = i;
                totalSize--;
                return queues[i].dequeue();
            }
        }
        return null;
    }

    /**
     * Просмотр следующего элемента без извлечения
     */
    public T peek() {
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].peek();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return totalSize == 0;
    }

    public int size() {
        return totalSize;
    }

    public int size(int priority) {
        if (priority < 0 || priority >= levels) {
            throw new IllegalArgumentException("Неверный приоритет");
        }
        return queues[priority].size();
    }

    public int getCurrentPriority() {
        return currentPriority;
    }

    public int getLevelsCount() {
        return levels;
    }

    public static void main(String[] args){

    }
}
