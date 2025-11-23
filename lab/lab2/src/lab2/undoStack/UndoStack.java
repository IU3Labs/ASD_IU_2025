package lab2.undoStack;

import lab2.utils.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UndoStack {
    private final List<Integer> data;

    private enum Type {PUSH, POP, NONE}

    private Type last;
    private int saved;
    private boolean canRedo;
    private boolean canUndo;

    public UndoStack() {
        data = new ArrayList<>();
        last = Type.NONE;
        canUndo = false;
        canRedo = false;
    }

    public void push(int newElement) {
        last = Type.PUSH;
        canUndo = true;
        data.add(newElement);
    }

    public Integer pop() {
        if (data.isEmpty()) throw new RuntimeException("Stack empty");
        last = Type.POP;
        saved = data.removeLast();
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
                data.add(saved);
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
            default:
                return;
        }
        canUndo = true;
        canRedo = false;
    }

    public Integer peek() {
        if (data.isEmpty()) throw new RuntimeException("Stack empty");
        return data.getLast();
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
    public static void main(String[] args) {
        UndoStack stack = new UndoStack();
        for (int i = 0; i < 4; i++) {
            stack.push(utils.random100());
        }
        stack.print();

        stack.undo();
        stack.print();
        stack.redo();
        stack.print();

        for (int i = 0; i < 2; i++) {
            stack.pop();
        }
        stack.printRevers();

        stack.undo();
        stack.printRevers();
        stack.redo();
        stack.printRevers();

    }
}
/*
[8, 35, 44, 59]
[8, 35, 44]
[8, 35, 44, 59]
[35, 8]
[44, 35, 8]
[35, 8]
 */