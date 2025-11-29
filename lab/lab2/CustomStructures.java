package lab2;

import java.util.ArrayList;
import java.util.List;

/*
 Базовый класс: структура с поддержкой undo/redo.
 Хранит историю состояний в виде снимков (копий списка элементов)
 */
abstract class UndoableStructure<T> {

    protected final List<T> elements = new ArrayList<>();

    private final List<List<T>> undoStack = new ArrayList<>();
    private final List<List<T>> redoStack = new ArrayList<>();

    //Делает снимок текущего состояния (deep copy).

    private List<T> snapshot() {
        return new ArrayList<>(elements);
    }

    // Сохраняет состояние перед изменением

    protected void saveState() {
        undoStack.add(snapshot());
        redoStack.clear(); // redo обнуляется после нового изменения
    }

    //Undo — откат к предыдущему состоянию

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Нечего отменять");
            return;
        }
        redoStack.add(snapshot());
        List<T> previous = undoStack.remove(undoStack.size() - 1);
        elements.clear();
        elements.addAll(previous);
    }

    // Redo — восстановление отменённого состояния

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Нечего вернуть");
            return;
        }
        undoStack.add(snapshot());
        List<T> next = redoStack.remove(redoStack.size() - 1);
        elements.clear();
        elements.addAll(next);
    }

    //Печать содержимого структуры

    public void print() {
        System.out.println(elements);
    }
}

//Собственный стек с поддержкой undo/redo

class CustomStack<T> extends UndoableStructure<T> {

    public void push(T value) {
        saveState();
        elements.add(value);
    }

    public T pop() {
        if (elements.isEmpty()) {
            return null;
        }
        saveState();
        return elements.remove(elements.size() - 1);
    }

    public T peek() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }
}

//Собственная двусторонняя очередь (deque) с поддержкой undo/redo

class CustomDeque<T> extends UndoableStructure<T> {

    public void addFirst(T value) {
        saveState();
        elements.add(0, value);
    }

    public void addLast(T value) {
        saveState();
        elements.add(value);
    }

    public T removeFirst() {
        if (elements.isEmpty()) {
            return null;
        }
        saveState();
        return elements.remove(0);
    }

    public T removeLast() {
        if (elements.isEmpty()) {
            return null;
        }
        saveState();
        return elements.remove(elements.size() - 1);
    }
}

public class CustomStructures {

    public static void main(String[] args) {
        System.out.println("===== ТЕСТ СТЕКА =====");
        CustomStack<Integer> stack = new CustomStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.print(); // [10, 20, 30]

        stack.undo();
        stack.print(); // [10, 20]

        stack.redo();
        stack.print(); // [10, 20, 30]

        stack.pop();
        stack.print(); // [10, 20]
        stack.undo();
        stack.print(); // [10, 20, 30]


        System.out.println("\n===== ТЕСТ ДВОЙНОЙ ОЧЕРЕДИ =====");
        CustomDeque<String> deque = new CustomDeque<>();

        deque.addLast("A");
        deque.addLast("B");
        deque.addFirst("C");
        deque.print(); // [C, A, B]

        deque.undo();
        deque.print(); // [A, B]

        deque.redo();
        deque.print(); // [C, A, B]

        deque.removeFirst();
        deque.print(); // [A, B]

        deque.undo();
        deque.print(); // [C, A, B]
    }
}
