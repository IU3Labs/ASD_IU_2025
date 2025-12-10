package lab2.tasks_b;

import java.util.ArrayList;
import java.util.Stack;

/**
 3 Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
 операций undo/redo. Прокомментировать код.
 */
public class UndoRedoStack {

    private enum Operation {
        PUSH,
        POP
    }

    private final Stack<Integer> stack;
    private final Stack<Integer> historyValues;    // История значений операций
    private final Stack<Operation> historyTypes;   // История типов операций
    private final Stack<Integer> undoneValues;     // Значения отмененных операций
    private final Stack<Operation> undoneTypes;    // Типы отмененных операций

    public UndoRedoStack() {
        stack = new Stack<>();
        historyValues = new Stack<>();
        historyTypes = new Stack<>();
        undoneValues = new Stack<>();
        undoneTypes = new Stack<>();
    }

    /**
     * Добавление элемента в стек
     */
    public int push(int value) {
        stack.push(value);
        historyValues.push(value);
        historyTypes.push(Operation.PUSH);
        clearRedoHistory(); // Новое действие очищает историю повтора
        return value;
    }

    /**
     * Удаление и возврат верхнего элемента стека
     */
    public int pop() {
        historyValues.push(stack.peek());
        historyTypes.push(Operation.POP);
        clearRedoHistory(); // Новое действие очищает историю повтора
        return stack.pop();
    }

    /**
     * Просмотр верхнего элемента без удаления
     */
    public int peek() {
        return stack.peek();
    }

    /**
     * Отмена последней операции
     */
    public void undo() {
        if (historyTypes.isEmpty()) {
            System.out.println("Нет операций для отмены");
            return;
        }

        int value = historyValues.pop();
        Operation type = historyTypes.pop();

        switch (type) {
            case POP -> {
                stack.push(value); // Отмена удаления - возвращаем элемент
                recordUndone(value, Operation.POP);
            }
            case PUSH -> {
                stack.pop(); // Отмена добавления - удаляем элемент
                recordUndone(value, Operation.PUSH);
            }
        }
    }

    /**
     * Повтор отмененной операции
     */
    public void redo() {
        if (undoneTypes.isEmpty()) {
            System.out.println("Нет операций для повтора");
            return;
        }

        int value = undoneValues.pop();
        Operation type = undoneTypes.pop();

        switch (type) {
            case POP -> {
                stack.pop(); // Повторяем удаление элемента
                recordHistory(value, Operation.POP);
            }
            case PUSH -> {
                stack.push(value); // Повторяем добавление элемента
                recordHistory(value, Operation.PUSH);
            }
        }
    }

    private void recordHistory(int value, Operation type) {
        historyValues.push(value);
        historyTypes.push(type);
    }

    private void recordUndone(int value, Operation type) {
        undoneValues.push(value);
        undoneTypes.push(type);
    }

    private void clearRedoHistory() {
        undoneValues.clear();
        undoneTypes.clear();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Вывод стека в прямом порядке (снизу вверх)
     */
    public void print() {
        System.out.println(stack);
    }

    /**
     * Вывод стека в обратном порядке (сверху вниз)
     */
    public void printReversed() {
        ArrayList<Integer> list = new ArrayList<>(stack);
        System.out.print("[");
        if (!list.isEmpty()) {
            for (int i = list.size() - 1; i > 0; i--) {
                System.out.print(list.get(i) + ", ");
            }
            System.out.print(list.get(0) + "]");
        } else {
            System.out.print("]");
        }
        System.out.println();
    }
}
