// Базовая очередь

package CircularQueue;

public abstract class BaseQueue {
    protected int size; // количество элементов в очереди

    public BaseQueue() {
        this.size = 0;
    }

    // Добавляет элемент в конец очереди
    public abstract void enqueue(int value);

    // Удаляет и возвращает элемент из начала очереди
    public abstract int dequeue();

    // Возвращает элемент из начала очереди без удаления
    public abstract int peek();

    // пустая очередь или нет
    public boolean isEmpty() {
        return size == 0;
    }

    // полная очередь или нет
    public int size() {
        return size;
    }

    // Выводит содержимое очереди
    public abstract void printQueue();
}