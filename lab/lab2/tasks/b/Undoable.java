package org.example.lab2.tasks.b;

/**
 * Интерфейс для структур, поддерживающих undo/redo последней операции.
 * Реализуем очень простой стек команд: две стопки операций — undoStack и redoStack.
 */
public interface Undoable {
    boolean undo();
    boolean redo();
}