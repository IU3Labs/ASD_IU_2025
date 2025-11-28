package tasks.task3;
import java.util.LinkedList;
import java.util.Stack;
import java.util.NoSuchElementException;


 // Реализация двусторонней очереди с поддержкой операций undo/redo
 // Использует LinkedList для эффективных операций с обоими концами
public class UndoableDeque<T> {
    private LinkedList<T> elements;      // Основное хранилище элементов
    private Stack<Operation<T>> undoStack; // Стек операций для отмены
    private Stack<Operation<T>> redoStack; // Стек операций для повтора

    public UndoableDeque() {
        elements = new LinkedList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }


     // Добавляет элемент в начало очереди

    public void addFirst(T element) {
        elements.addFirst(element);

        Operation<T> operation = new Operation<>(Operation.Type.ADD_FIRST, element);
        undoStack.push(operation);
        redoStack.clear();

        System.out.println("Added first: " + element + ", Deque: " + elements);
    }


     // Добавляет элемент в конец очереди

    public void addLast(T element) {
        elements.addLast(element);

        Operation<T> operation = new Operation<>(Operation.Type.ADD_LAST, element);
        undoStack.push(operation);
        redoStack.clear();

        System.out.println("Added last: " + element + ", Deque: " + elements);
    }

    // Удаляет и возвращает элемент из начала очереди

    public T removeFirst() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T element = elements.removeFirst();

        Operation<T> operation = new Operation<>(Operation.Type.REMOVE_FIRST, null, element);
        undoStack.push(operation);
        redoStack.clear();

        System.out.println("Removed first: " + element + ", Deque: " + elements);
        return element;
    }

    // Удаляет и возвращает элемент из конца очереди
    public T removeLast() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T element = elements.removeLast();

        Operation<T> operation = new Operation<>(Operation.Type.REMOVE_LAST, null, element);
        undoStack.push(operation);
        redoStack.clear();

        System.out.println("Removed last: " + element + ", Deque: " + elements);
        return element;
    }

    // Возвращает первый элемент без удаления
    public T getFirst() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements.getFirst();
    }

    // Возвращает последний элемент без удаления
    public T getLast() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements.getLast();
    }

    // Отменяет последнюю операцию
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }

        Operation<T> operation = undoStack.pop();
        System.out.println("Undoing: " + operation.getType());

        switch (operation.getType()) {
            case ADD_FIRST:
                // Отмена добавления в начало - удаляем первый элемент
                if (!elements.isEmpty()) {
                    T removed = elements.removeFirst();
                    Operation<T> redoOp = new Operation<>(Operation.Type.REMOVE_FIRST, null, removed);
                    redoStack.push(redoOp);
                }
                break;

            case ADD_LAST:
                // Отмена добавления в конец - удаляем последний элемент
                if (!elements.isEmpty()) {
                    T removed = elements.removeLast();
                    Operation<T> redoOp = new Operation<>(Operation.Type.REMOVE_LAST, null, removed);
                    redoStack.push(redoOp);
                }
                break;

            case REMOVE_FIRST:
                // Отмена удаления из начала - добавляем элемент обратно в начало
                T elementToAddFirst = operation.getPreviousState();
                elements.addFirst(elementToAddFirst);
                Operation<T> redoOpFirst = new Operation<>(Operation.Type.ADD_FIRST, elementToAddFirst);
                redoStack.push(redoOpFirst);
                break;

            case REMOVE_LAST:
                // Отмена удаления из конца - добавляем элемент обратно в конец
                T elementToAddLast = operation.getPreviousState();
                elements.addLast(elementToAddLast);
                Operation<T> redoOpLast = new Operation<>(Operation.Type.ADD_LAST, elementToAddLast);
                redoStack.push(redoOpLast);
                break;
        }

        System.out.println("After undo: " + elements);
    }

    // Повторяет последнюю отмененную операцию
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }

        Operation<T> operation = redoStack.pop();
        System.out.println("Redoing: " + operation.getType());

        switch (operation.getType()) {
            case ADD_FIRST:
                elements.addFirst(operation.getElement());
                undoStack.push(new Operation<>(Operation.Type.ADD_FIRST, operation.getElement()));
                break;

            case ADD_LAST:
                elements.addLast(operation.getElement());
                undoStack.push(new Operation<>(Operation.Type.ADD_LAST, operation.getElement()));
                break;

            case REMOVE_FIRST:
                if (!elements.isEmpty()) {
                    T removed = elements.removeFirst();
                    undoStack.push(new Operation<>(Operation.Type.REMOVE_FIRST, null, removed));
                }
                break;

            case REMOVE_LAST:
                if (!elements.isEmpty()) {
                    T removed = elements.removeLast();
                    undoStack.push(new Operation<>(Operation.Type.REMOVE_LAST, null, removed));
                }
                break;
        }

        System.out.println("After redo: " + elements);
    }

    // Проверяет, доступна ли операция undo
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    // Проверяет, доступна ли операция redo
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    // Проверяет, пуста ли очередь
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // Возвращает размер очереди
    public int size() {
        return elements.size();
    }

    // Очищает очередь и историю операций
    public void clear() {
        elements.clear();
        undoStack.clear();
        redoStack.clear();
        System.out.println("Deque cleared");
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    // Выводит информацию о состоянии истории операций
    public void printHistory() {
        System.out.println("Undo stack: " + undoStack);
        System.out.println("Redo stack: " + redoStack);
    }
}