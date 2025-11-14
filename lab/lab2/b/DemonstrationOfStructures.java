package lab2.b;

/**
 * Демонстрация работы структур
 **/

public class DemonstrationOfStructures {
    public static void main(String[] args) {
        System.out.println("\nQueueOfQueue\n");
        QueueOfQueue queueOfQueue = new QueueOfQueue(4);
        queueOfQueue.add(4);
        queueOfQueue.add(3);
        queueOfQueue.add(2);
        queueOfQueue.add(6);
        queueOfQueue.print();
        queueOfQueue.remove();
        queueOfQueue.remove();
        queueOfQueue.remove();
        queueOfQueue.add(100);
        queueOfQueue.print();
        queueOfQueue.add(101);
        queueOfQueue.add(102);
        queueOfQueue.add(103); // Не добавится, т.к. в очереди нет места.
        queueOfQueue.print();

        System.out.println("\nLRUCache\n");
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.print();
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(4, "D"); // Пара (3, "C") должна удалиться, так как менее используемая.
        lruCache.print();

        System.out.println("\nUndoRedoStack\n");
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        undoRedoStack.push(1);
        undoRedoStack.push(2);
        undoRedoStack.push(3);
        undoRedoStack.print();
        System.out.println("Отменяем два последних действия");
        undoRedoStack.undo();
        undoRedoStack.undo();
        undoRedoStack.print();
        System.out.println("Возвращаем одно действие и делаем pop(), после чего пытаемся сделать три раза redo()");
        undoRedoStack.redo();
        undoRedoStack.pop();
        undoRedoStack.redo();
        undoRedoStack.redo();
        undoRedoStack.redo();
        undoRedoStack.print(); // Элемент вернулся, после pop() удалился и история redo очистилась.
        undoRedoStack.undo();
        System.out.println("Отменяем pop()");
        undoRedoStack.print();

        System.out.println("\nUndoRedoDeque\n");
        UndoRedoDEQueue undoRedoDEQueue = new UndoRedoDEQueue(4);
        undoRedoDEQueue.addLast(3);
        undoRedoDEQueue.addFirst(2);
        undoRedoDEQueue.addLast(4);
        undoRedoDEQueue.addFirst(1);
        undoRedoDEQueue.print();
        undoRedoDEQueue.removeLast();
        undoRedoDEQueue.undo();
        undoRedoDEQueue.undo();
        undoRedoDEQueue.print();
        undoRedoDEQueue.redo();
        undoRedoDEQueue.print();
        undoRedoDEQueue.redo();
        undoRedoDEQueue.print();
        undoRedoDEQueue.redo();
        undoRedoDEQueue.redo();
        undoRedoDEQueue.print();
    }
}