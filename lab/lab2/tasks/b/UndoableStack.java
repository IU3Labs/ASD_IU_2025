package org.example.lab2.tasks.b;

import java.util.*;

/**
 * Стек с поддержкой undo/redo.
 */
public class UndoableStack<E> {
    private final Deque<E> stack = new ArrayDeque<>();
    private final Deque<Runnable> undo = new ArrayDeque<>();
    private final Deque<Runnable> redo = new ArrayDeque<>();

    public void push(E e) {
        stack.push(e);
        undo.push(() -> stack.pop());
        redo.clear();
    }

    public E pop() {
        E val = stack.pop();
        undo.push(() -> stack.push(val));
        redo.clear();
        return val;
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
        return stack.toString();
    }

    public static void main(String[] args) {
        UndoableStack<Integer> st = new UndoableStack<>();
        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println("stack: " + st.printForward());
        st.undo();
        System.out.println("после undo: " + st.printForward());
        st.redo();
        System.out.println("после redo: " + st.printForward());
    }
}