/**
 * Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
 * операций undo/redo. Прокомментировать код.
 * Примечание. Undo отменяет эффект последней команды. Redo, в свою
 * очередь, повторно выполняет команду, отменённую при откате.
 */
package tasks.task3;

public class TaskB3 {
    public static void taskB3() {
        System.out.println("----- UndoableStack -----");
        demoStack();

        System.out.println("\n----- UndoableDeque -----");
        demoDeque();
    }

    private static void demoStack() {
        UndoableStack<Integer> stack = new UndoableStack<>();

        // Выполняем операции со стеком
        System.out.println("\n--- Выполняем операции со стеком ---");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.pop();
        stack.push(40);

        // Тестируем undo
        System.out.println("\n--- Тестируем undo ---");
        stack.undo(); // Отменяем push(40)
        stack.undo(); // Отменяем pop()
        stack.undo(); // Отменяем push(30)

        // Тестируем redo
        System.out.println("\n--- Тестируем redo ---");
        stack.redo(); // Повторяем push(30)
        stack.redo(); // Повторяем pop()
        stack.redo(); // Повторяем push(40)

        // Показываем финальное состояние
        System.out.println("\n--- Финальное состояние ---");
        System.out.println("Стек: " + stack);
        System.out.println("Can undo: " + stack.canUndo());
        System.out.println("Can redo: " + stack.canRedo());
    }

    private static void demoDeque() {
        UndoableDeque<String> deque = new UndoableDeque<>();

        // Выполняем операции с deque
        System.out.println("\n--- Выполняем операции с deque ---");
        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");
        deque.addLast("D");

        deque.removeFirst();
        deque.removeLast();

        // Тестируем undo/redo
        System.out.println("\n--- Тестируем undo ---");
        deque.undo(); // Отменяем removeLast
        deque.undo(); // Отменяем removeFirst

        System.out.println("\n--- Тестируем redo ---");
        deque.redo(); // Повторяем removeFirst
        deque.redo(); // Повторяем removeLast

        // Дополнительные операции
        System.out.println("\n--- Дополнительные операции ---");
        deque.addFirst("X");
        deque.addLast("Y");
        deque.undo();
        deque.undo();

        // Показываем финальное состояние
        System.out.println("\n--- Финальное состояние ---");
        System.out.println("Deque: " + deque);
        System.out.println("Can undo: " + deque.canUndo());
        System.out.println("Can redo: " + deque.canRedo());
    }
}
