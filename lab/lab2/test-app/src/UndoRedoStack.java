/*Группа B, задание 3: Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
операций undo/redo. Прокомментировать код.
Примечание. Undo отменяет эффект последней команды. Redo, в свою
очередь, повторно выполняет команду, отменённую при откате.*/

import java.util.*;

public class UndoRedoStack<T> {

    /*
     Логика: Используем два стека - для undo и redo
     Каждая операция сохраняется как команда с информацией об обратном действии
     */
    interface Command<T> {
        void execute();
        void undo();
    }

    private final Stack<T> stack;
    private final Stack<Command<T>> undoStack;
    private final Stack<Command<T>> redoStack;

    public UndoRedoStack() {
        this.stack = new Stack<>();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void push(T element) {
        stack.push(element);

        undoStack.push(new Command<T>() {
            public void execute() { /* Не используется для push */ }
            public void undo() {
                stack.pop();
            }
        });

        redoStack.clear();
    }

    public T pop() {
        if (stack.isEmpty()) return null;

        T element = stack.pop();

        undoStack.push(new Command<T>() {
            public void execute() { /* Не используется для pop */ }
            public void undo() {
                stack.push(element);
            }
        });

        redoStack.clear();
        return element;
    }

    public T peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;

        Command<T> command = undoStack.pop();
        command.undo();

        redoStack.push(command);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;

        Command<T> command = redoStack.pop();

        undoStack.push(command);

        if (stack.isEmpty()) {

        } else {
            stack.pop();
        }

        return true;
    }

    public void print() {
        System.out.println("Stack: " + stack);
    }
}

class UndoRedoDeque<T> {
    private final LinkedList<T> deque;
    private final Stack<Runnable> undoStack;
    private final Stack<Runnable> redoStack;

    public UndoRedoDeque() {
        this.deque = new LinkedList<>();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addFirst(T element) {
        deque.addFirst(element);
        undoStack.push(() -> deque.removeFirst());
        redoStack.clear();
    }

    public void addLast(T element) {
        deque.addLast(element);
        undoStack.push(() -> deque.removeLast());
        redoStack.clear();
    }

    public T removeFirst() {
        if (deque.isEmpty()) return null;
        T element = deque.removeFirst();
        undoStack.push(() -> deque.addFirst(element));
        redoStack.clear();
        return element;
    }

    public T removeLast() {
        if (deque.isEmpty()) return null;
        T element = deque.removeLast();
        undoStack.push(() -> deque.addLast(element));
        redoStack.clear();
        return element;
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        Runnable action = undoStack.pop();
        action.run();
        redoStack.push(action);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        Runnable action = redoStack.pop();
        action.run();
        undoStack.push(action);
        return true;
    }

    public void print() {
        System.out.println("Deque: " + deque);
    }

    public static void main(String[] args) {
        System.out.println("Стек с Undo/Redo");
        UndoRedoStack<Integer> stack = new UndoRedoStack<>();
        stack.push(1);
        stack.push(2);
        stack.print(); // [1, 2]

        stack.undo();
        stack.print(); // [1]

        stack.redo();
        stack.print(); // [1, 2]

        System.out.println("\nДек с Undo/Redo");
        UndoRedoDeque<String> deque = new UndoRedoDeque<>();
        deque.addFirst("A");
        deque.addLast("B");
        deque.print(); // [A, B]

        deque.undo();
        deque.print(); // [A]

        deque.redo();
        deque.print(); // [A, B]
    }
}