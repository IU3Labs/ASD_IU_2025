package groupb;

import java.util.Stack;

public class UndoRedoStack {
    private Stack<Integer> stack;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public UndoRedoStack() {
        stack = new Stack<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        undoStack.push("push:" + value);
        redoStack.clear();
    }

    public int pop() {
        if (stack.isEmpty()) throw new RuntimeException("Stack is empty");
        int value = stack.pop();
        undoStack.push("pop:" + value);
        redoStack.clear();
        return value;
    }

    public void undo() {
        if (undoStack.isEmpty()) return;

        String operation = undoStack.pop();
        String[] parts = operation.split(":");
        String action = parts[0];
        int value = Integer.parseInt(parts[1]);

        if (action.equals("push")) {
            stack.pop();
            redoStack.push("push:" + value);
        } else if (action.equals("pop")) {
            stack.push(value);
            redoStack.push("pop:" + value);
        }
    }

    public void redo() {
        if (redoStack.isEmpty()) return;

        String operation = redoStack.pop();
        String[] parts = operation.split(":");
        String action = parts[0];
        int value = Integer.parseInt(parts[1]);

        if (action.equals("push")) {
            stack.push(value);
            undoStack.push("push:" + value);
        } else if (action.equals("pop")) {
            stack.pop();
            undoStack.push("pop:" + value);
        }
    }

    public void printStack() {
        System.out.println("Stack: " + stack);
    }

    public static void main(String[] args) {
        UndoRedoStack stack = new UndoRedoStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.printStack();

        stack.pop();
        stack.printStack();

        stack.undo();
        stack.printStack();

        stack.redo();
        stack.printStack();
    }
}