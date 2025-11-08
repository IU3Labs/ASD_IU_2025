package lab2.undoDeque;

import lab2.utils.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UndoDeque {
    private final List<Integer> data;

    private enum Type {PUSH,POP,POPF,PUSHF,NONE}

    private Type last;
    private int saved;
    private boolean canRedo;
    private boolean canUndo;

    public UndoDeque() {
        data = new ArrayList<>();
        last = Type.NONE;
        canUndo = false;
        canRedo = false;
    }

    public void push(int newElement) {
        last = Type.PUSH;
        canUndo = true;
        data.addLast(newElement);
    }

    public Integer pop() {
        if (data.isEmpty()) throw new RuntimeException("Deque empty");
        last = Type.POP;
        saved = data.removeLast();
        canUndo = true;
        return saved;
    }

    public void pushFront(int newElement) {
        last = Type.PUSHF;
        canUndo = true;
        data.addFirst(newElement);
    }

    public Integer popFront() {
        if (data.isEmpty()) throw new RuntimeException("Deque empty");
        last = Type.POPF;
        saved = data.removeFirst();
        canUndo = true;
        return saved;
    }

    public void undo() {
        if (!canUndo) return;
        switch (last) {
            case Type.PUSH:
                saved = data.removeLast();
                break;
            case Type.POP:
                data.addLast(saved);
                break;
            case Type.PUSHF:
                saved = data.removeFirst();
                break;
            case Type.POPF:
                data.addFirst(saved);
                break;
            default:
                return;
        }
        canUndo = false;
        canRedo = true;
    }

    public void redo() {
        if (!canRedo) return;
        switch (last) {
            case Type.PUSH:
                data.add(saved);
                break;
            case Type.POP:
                saved = data.removeLast();
                break;
            case Type.PUSHF:
                data.addFirst(saved);
                break;
            case Type.POPF:
                saved = data.removeFirst();
                break;
            default:
                return;
        }
        canUndo = true;
        canRedo = false;
    }

    public Integer peek() {
        if (data.isEmpty()) throw new RuntimeException("Deque empty");
        return data.getLast();
    }
    public Integer peekFront() {
        if (data.isEmpty()) throw new RuntimeException("Deque empty");
        return data.getFirst();
    }

    public Integer size() {
        return data.size();
    }

    public void print() {
        System.out.println(data.toString());
    }

    public void printRevers() {
        ArrayList<Integer> list = new ArrayList<>(data);
        Collections.reverse(list);
        System.out.println(list.toString());
    }
}

class TestUndoDeque {
    void main(String[] args) {
        UndoDeque stack = new UndoDeque();
        for (int i = 0; i < 4; i++) {
            stack.push(utils.random100());
        }
        stack.print();

        stack.undo();
        stack.print();
        stack.redo();
        stack.print();

        for (int i = 0; i < 2; i++) {
            stack.popFront();
        }
        stack.printRevers();

        stack.undo();
        stack.printRevers();
        stack.redo();
        stack.printRevers();


    }
}
/*
[80, 56, 68, 25]
[80, 56, 68]
[80, 56, 68, 25]
[25, 68]
[25, 68, 56]
[25, 68]
 */