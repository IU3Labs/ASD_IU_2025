package tasks;

import tasks.UndoRedoStack;

public class UndoRedoStackDemo {
    public static void main(String[] args) {
        UndoRedoStack stack = new UndoRedoStack();

        System.out.println("=== UNDO/REDO STACK ===\n");

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.print("После push(1,2,3): ");
        stack.printForward();

        stack.undo();
        System.out.print("После undo: ");
        stack.printForward();

        stack.redo();
        System.out.print("После redo: ");
        stack.printForward();

        stack.pop();
        System.out.print("После pop: ");
        stack.printForward();

        stack.undo();
        System.out.print("После undo pop: ");
        stack.printForward();

        System.out.println("\nВнутреннее состояние:");
        stack.printInternalState();
    }
}