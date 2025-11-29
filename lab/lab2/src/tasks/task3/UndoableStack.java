package tasks.task3;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.EmptyStackException;


 // Реализация стека с поддержкой операций undo/redo
 // Хранит историю операций в двух стеках для undo и redo

public class UndoableStack<T> {
    private List<T> elements;           // Основное хранилище элементов
    private Stack<Operation<T>> undoStack; // Стек операций для отмены
    private Stack<Operation<T>> redoStack; // Стек операций для повтора

    public UndoableStack() {
        elements = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Добавляет элемент в стек и сохраняет операцию в историю
    public void push(T element) {
        // Выполняем операцию
        elements.add(element);

        // Сохраняем операцию в undo стек
        Operation<T> operation = new Operation<>(Operation.Type.PUSH, element);
        undoStack.push(operation);

        // Очищаем redo стек при новой операции
        redoStack.clear();

        System.out.println("Pushed: " + element + ", Stack: " + elements);
    }

    //Удаляет и возвращает верхний элемент стека

    public T pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }

        // Выполняем операцию
        T element = elements.removeLast();

        // Сохраняем операцию в undo стек (сохраняем удаленный элемент для отмены)
        Operation<T> operation = new Operation<>(Operation.Type.POP, null, element);
        undoStack.push(operation);

        // Очищаем redo стек
        redoStack.clear();

        System.out.println("Popped: " + element + ", Stack: " + elements);
        return element;
    }

    // Возвращает верхний элемент стека без удаления
    public T peek() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
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
            case PUSH:
                // Отмена push - удаляем последний элемент
                if (!elements.isEmpty()) {
                    T removed = elements.removeLast();
                    // Сохраняем в redo стек операцию с удаленным элементом
                    Operation<T> redoOp = new Operation<>(Operation.Type.POP, null, removed);
                    redoStack.push(redoOp);
                }
                break;

            case POP:
                // Отмена pop - добавляем обратно удаленный элемент
                T elementToRestore = operation.getPreviousState();
                elements.add(elementToRestore);
                // Сохраняем в redo стек операцию push
                Operation<T> redoOp = new Operation<>(Operation.Type.PUSH, elementToRestore);
                redoStack.push(redoOp);
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
            case POP:
                // Повтор push - добавляем элемент
                elements.add(operation.getPreviousState());
                // Сохраняем в undo стек
                undoStack.push(new Operation<>(Operation.Type.PUSH, operation.getElement()));
                break;

            case PUSH:
                // Повтор pop - удаляем последний элемент
                if (!elements.isEmpty()) {
                    T removed = elements.removeLast();
                    // Сохраняем в undo стек
                    undoStack.push(new Operation<>(Operation.Type.POP, null, removed));
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

    // Проверяет, пуст ли стек
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // Возвращает размер стека
    public int size() {
        return elements.size();
    }

    // Очищает стек и историю операций
    public void clear() {
        elements.clear();
        undoStack.clear();
        redoStack.clear();
        System.out.println("Stack cleared");
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
