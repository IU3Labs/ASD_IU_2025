package tasks;
import java.util.Stack;

public class UndoRedoStack {
    private enum Operation {
        PUSH, POP
    }

    private final Stack<Integer> stack;
    private final Stack<Operation> historyTypes;
    private final Stack<Integer> historyValues;
    private final Stack<Operation> redoTypes;
    private final Stack<Integer> redoValues;

    public UndoRedoStack() {
        stack = new Stack<>();
        historyTypes = new Stack<>();
        historyValues = new Stack<>();
        redoTypes = new Stack<>();
        redoValues = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        historyTypes.push(Operation.PUSH);
        historyValues.push(value);
        clearRedoHistory();
    }

    public int pop() {
        if (stack.isEmpty()) {
            System.out.println("Стек пуст");
            return -1;
        }

        int value = stack.pop();
        historyTypes.push(Operation.POP);
        historyValues.push(value);
        clearRedoHistory();
        return value;
    }

    public int peek() {
        if (stack.isEmpty()) {
            System.out.println("Стек пуст");
            return -1;
        }
        return stack.peek();
    }

    public void undo() {
        if (historyTypes.isEmpty()) {
            System.out.println("Нечего отменять");
            return;
        }

        Operation type = historyTypes.pop();
        int value = historyValues.pop();

        switch (type) {
            case POP:
                stack.push(value);
                redoTypes.push(Operation.POP);
                redoValues.push(value);
                break;
            case PUSH:
                int removedValue = stack.pop();
                redoTypes.push(Operation.PUSH);
                redoValues.push(removedValue);
                break;
        }
    }

    public void redo() {
        if (redoTypes.isEmpty()) {
            System.out.println("Нечего повторять");
            return;
        }

        Operation type = redoTypes.pop();
        int value = redoValues.pop();

        switch (type) {
            case POP:
                stack.pop();
                break;
            case PUSH:
                stack.push(value);
                break;
        }

        historyTypes.push(type);
        historyValues.push(value);
    }

    private void clearRedoHistory() {
        redoTypes.clear();
        redoValues.clear();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void printForward() {
        System.out.println("Стек (снизу вверх): " + stack);
    }

    public void printBackward() {
        Stack<Integer> temp = new Stack<>();
        Stack<Integer> copy = (Stack<Integer>) stack.clone();

        System.out.print("Стек (сверху вниз): [");
        while (!copy.isEmpty()) {
            int value = copy.pop();
            System.out.print(value);
            if (!copy.isEmpty()) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void printInternalState() {
        System.out.println("Стек: " + stack);
        System.out.println("История: " + historyTypes + " -> " + historyValues);
        System.out.println("Redo: " + redoTypes + " -> " + redoValues);
        System.out.println("Размер: " + size() + ", Пуст: " + isEmpty());
    }
}