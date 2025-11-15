package tasks;
import tasks.CircularQueue;

public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);

        System.out.println("=== ЦИКЛИЧЕСКАЯ ОЧЕРЕДЬ ===");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.print("Добавили 1,2,3: ");
        queue.print();

        System.out.println("Удален: " + queue.dequeue());
        System.out.print("После удаления: ");
        queue.print();

        queue.enqueue(4);
        queue.enqueue(5);
        System.out.print("Добавили 4,5: ");
        queue.print();

        System.out.println("Обратный порядок: ");
        queue.printReversed();

        System.out.println("Внутреннее состояние:");
        queue.printInternalState();

        System.out.println("Очистка очереди:");
        while (!queue.isEmpty()) {
            System.out.println("Удален: " + queue.dequeue());
        }
        queue.print();
    }
}