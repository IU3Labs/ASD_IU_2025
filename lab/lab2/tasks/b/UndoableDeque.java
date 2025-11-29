package org.example.lab2.tasks.b;

import java.util.*;

/**
 * Двусторонняя очередь с undo/redo.
 */
public class UndoableDeque<E> {
    private final Deque<E> deque = new ArrayDeque<>();
    private final Deque<Runnable> undo = new ArrayDeque<>();
    private final Deque<Runnable> redo = new ArrayDeque<>();

    public void addFirst(E e) {
        deque.addFirst(e);
        undo.push(() -> deque.removeFirst());
        redo.clear();
    }

    public void addLast(E e) {
        deque.addLast(e);
        undo.push(() -> deque.removeLast());
        redo.clear();
    }

    public E removeFirst() {
        E x = deque.removeFirst();
        undo.push(() -> deque.addFirst(x));
        redo.clear();
        return x;
    }

    public E removeLast() {
        E x = deque.removeLast();
        undo.push(() -> deque.addLast(x));
        redo.clear();
        return x;
    }

    public boolean undo() {
        if (undo.isEmpty()) { return false; }
        Runnable act = undo.pop();
        act.run();
        redo.push(act);
        return true;
    }

    public boolean redo() {
        if (redo.isEmpty()) { return false; }
        Runnable act = redo.pop();
        act.run();
        undo.push(act);
        return true;
    }

    public String printForward() {
        return deque.toString();
    }

    public static void main(String[] args) {
        UndoableDeque<String> dq = new UndoableDeque<>();
        dq.addFirst("A");
        dq.addLast("B");
        dq.addLast("C");
        System.out.println("deque: " + dq.printForward());
        dq.undo();
        System.out.println("after undo: " + dq.printForward());
        dq.redo();
        System.out.println("after redo: " + dq.printForward());
        dq.removeFirst();
        System.out.println("after removeFirst: " + dq.printForward());
    }
}