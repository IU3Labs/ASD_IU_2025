import java.util.*;

/**
 * Стек с поддержкой операций Undo/Redo.
 * Undo отменяет последнюю операцию, Redo повторно выполняет отменённую операцию.
 */
public class UndoRedoStack<T> {
    private Stack<T> mainStack;      // Основной стек
    private Stack<T> undoStack;      // Стек отменённых операций (для Redo)
    private Stack<String> operationHistory;  // История операций для отладки

    public UndoRedoStack() {
        this.mainStack = new Stack<>();
        this.undoStack = new Stack<>();
        this.operationHistory = new Stack<>();
    }

    /**
     * Добавление элемента на стек
     */
    public void push(T element) {
        mainStack.push(element);
        undoStack.clear();  // При новой операции очищаем историю Redo
        operationHistory.push("PUSH: " + element);
    }

    /**
     * Удаление элемента со стека
     */
    public T pop() {
        if (mainStack.isEmpty()) {
            throw new EmptyStackException();
        }
        T element = mainStack.pop();
        undoStack.clear();  // При новой операции очищаем историю Redo
        operationHistory.push("POP: " + element);
        return element;
    }

    /**
     * Просмотр верхнего элемента без удаления
     */
    public T peek() {
        return mainStack.isEmpty() ? null : mainStack.peek();
    }

    /**
     * Отмена последней операции (Undo)
     * - Если последняя операция была PUSH, то удаляем элемент
     * - Если последняя операция была POP, то возвращаем элемент
     */
    public void undo() {
        if (operationHistory.isEmpty()) {
            System.out.println("Нечего отменять!");
            return;
        }

        String lastOperation = operationHistory.pop();

        if (lastOperation.startsWith("PUSH")) {
            // Отмена PUSH: удаляем элемент со стека
            if (!mainStack.isEmpty()) {
                T removed = mainStack.pop();
                undoStack.push(removed);
                operationHistory.push("UNDO_PUSH: " + removed);
            }
        } else if (lastOperation.startsWith("POP")) {
            // Отмена POP: восстанавливаем элемент
            String valueStr = lastOperation.substring(5).trim();
            operationHistory.push("UNDO_POP: " + valueStr);
        }
    }

    /**
     * Повтор отменённой операции (Redo)
     */
    public void redo() {
        if (undoStack.isEmpty()) {
            System.out.println("Нечего повторять!");
            return;
        }

        T element = undoStack.pop();
        mainStack.push(element);
        operationHistory.push("REDO_PUSH: " + element);
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    public int size() {
        return mainStack.size();
    }

    /**
     * Печать состояния стека и истории операций
     */
    public void print() {
        System.out.println("\n=== Стек с Undo/Redo ===");
        System.out.println("Основной стек: " + mainStack);
        System.out.println("Undo стек: " + undoStack);
        System.out.println("История операций: " + operationHistory);
    }

    // Демонстрация работы
    public static void main(String[] args) {
        UndoRedoStack<Integer> stack = new UndoRedoStack<>();

        System.out.println("--- Добавление элементов ---");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.print();

        System.out.println("\n--- Удаление элемента ---");
        stack.pop();
        stack.print();

        System.out.println("\n--- Undo (отмена POP) ---");
        stack.undo();
        stack.print();

        System.out.println("\n--- Undo (отмена PUSH 30) ---");
        stack.undo();
        stack.print();

        System.out.println("\n--- Redo (повтор PUSH 30) ---");
        stack.redo();
        stack.print();

        System.out.println("\n--- Добавление нового элемента ---");
        stack.push(40);
        stack.print();
    }
}
