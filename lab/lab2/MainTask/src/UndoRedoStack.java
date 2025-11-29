import java.util.Stack;

public class UndoRedoStack<T> {
    private Stack<T> stack = new Stack<>();

    private Stack<Runnable> undoStack = new Stack<>();
    private Stack<Runnable> redoStack = new Stack<>();

    public void push(T value) {
        stack.push(value);
        undoStack.push(() -> stack.pop());
        redoStack.clear();
    }

    public T pop() {
        if (stack.isEmpty()) return null;
        T val = stack.pop();
        undoStack.push(() -> stack.push(val));
        redoStack.clear();
        return val;
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Нечего отменять");
            return;
        }
        Runnable undoAction = undoStack.pop();

        undoAction.run();
    }

    public void print() {
        System.out.println("Стек: " + stack);
    }

    public static void main(String[] args) {
        UndoRedoStack<Integer> us = new UndoRedoStack<>();
        us.push(10);
        us.push(20);
        us.print();

        System.out.println("Pop: " + us.pop());
        us.print();

        System.out.println("Undo (отмена pop):");
        us.undo();
        us.print();

        System.out.println("Undo (отмена push 20):");
        us.undo();
        us.print();
    }
}
