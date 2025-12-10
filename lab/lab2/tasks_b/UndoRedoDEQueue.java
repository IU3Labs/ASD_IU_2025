package lab2.tasks_b;

import java.util.Stack;

/**
 3 Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
 операций undo/redo. Прокомментировать код.
 */
public class UndoRedoDEQueue {

    private enum Operation {
        ADD_FIRST,
        ADD_LAST,
        REMOVE_FIRST,
        REMOVE_LAST
    }

    // Структура кольцевого буфера
    private int front; // Указатель на начало очереди
    private int rear;  // Указатель на конец очереди
    private final int[] data; // Массив для хранения элементов
    private int size;  // Текущее количество элементов

    // Система истории операций
    private final Stack<Integer> historyValues;  // История значений
    private final Stack<Operation> historyTypes; // История типов операций
    private final Stack<Integer> undoneValues;   // Отмененные значения
    private final Stack<Operation> undoneTypes;  // Отмененные типы операций

    public UndoRedoDEQueue(int capacity) {
        // Инициализация массива data
        data = new int[capacity];

        // Инициализация остальных полей
        front = -1;
        rear = -1;
        size = 0;
        historyValues = new Stack<>();
        historyTypes = new Stack<>();
        undoneValues = new Stack<>();
        undoneTypes = new Stack<>();
    }

    public boolean isFull() {
        return (front == 0 && rear == data.length - 1) || front == rear + 1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    /**
     * Добавление элемента в начало очереди
     */
    public void addFirst(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }

        data[front] = value;
        size++;
        recordAction(value, Operation.ADD_FIRST);
    }

    /**
     * Добавление элемента в конец очереди
     */
    public void addLast(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = value;
        size++;
        recordAction(value, Operation.ADD_LAST);
    }

    /**
     * Удаление и возврат первого элемента
     */
    public int removeFirst() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int value = data[front];
        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (front == data.length - 1) {
            front = 0;
            size--;
        } else {
            front++;
            size--;
        }

        recordAction(value, Operation.REMOVE_FIRST);
        return value;
    }

    /**
     * Удаление и возврат последнего элемента
     */
    public int removeLast() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int value = data[rear];
        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (rear == 0) {
            rear = data.length - 1;
            size--;
        } else {
            rear--;
            size--;
        }

        recordAction(value, Operation.REMOVE_LAST);
        return value;
    }

    private void recordAction(int value, Operation type) {
        historyValues.push(value);
        historyTypes.push(type);
        clearRedoHistory(); // Новое действие сбрасывает историю повтора
    }

    private void clearRedoHistory() {
        undoneValues.clear();
        undoneTypes.clear();
    }

    public int size() {
        return size;
    }

    /**
     * Вывод очереди от начала к концу
     */
    public void print() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size - 1; i++) {
                int index = (front + i) % data.length;
                System.out.print(data[index] + ", ");
            }
            System.out.print(data[(front + size - 1) % data.length]);
            System.out.println("]");
        }
    }

    /**
     * Вывод очереди от конца к началу
     */
    public void printReversed() {
        if (size == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                int index = (rear - i + data.length) % data.length;
                System.out.print(data[index]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    /**
     * Отмена последней операции
     */
    public void undo() {
        if (historyTypes.isEmpty()) {
            System.out.println("Нет операций для отмены");
            return;
        }

        int value = historyValues.pop();
        Operation type = historyTypes.pop();

        switch (type) {
            case REMOVE_LAST -> {
                addLastInternal(value); // Отмена удаления с конца
                recordUndone(value, Operation.REMOVE_LAST);
            }
            case ADD_LAST -> {
                removeLastInternal(); // Отмена добавления в конец
                recordUndone(value, Operation.ADD_LAST);
            }
            case REMOVE_FIRST -> {
                addFirstInternal(value); // Отмена удаления с начала
                recordUndone(value, Operation.REMOVE_FIRST);
            }
            case ADD_FIRST -> {
                removeFirstInternal(); // Отмена добавления в начало
                recordUndone(value, Operation.ADD_FIRST);
            }
        }
    }

    /**
     * Повтор отмененной операции
     */
    public void redo() {
        if (undoneTypes.isEmpty()) {
            System.out.println("Нет операций для повтора");
            return;
        }

        int value = undoneValues.pop();
        Operation type = undoneTypes.pop();

        switch (type) {
            case REMOVE_LAST -> {
                removeLastInternal(); // Повтор удаления с конца
                recordHistory(value, Operation.REMOVE_LAST);
            }
            case ADD_LAST -> {
                addLastInternal(value); // Повтор добавления в конец
                recordHistory(value, Operation.ADD_LAST);
            }
            case REMOVE_FIRST -> {
                removeFirstInternal(); // Повтор удаления с начала
                recordHistory(value, Operation.REMOVE_FIRST);
            }
            case ADD_FIRST -> {
                addFirstInternal(value); // Повтор добавления в начало
                recordHistory(value, Operation.ADD_FIRST);
            }
        }
    }

    private void recordHistory(int value, Operation type) {
        historyValues.push(value);
        historyTypes.push(type);
    }

    private void recordUndone(int value, Operation type) {
        undoneValues.push(value);
        undoneTypes.push(type);
    }

    // Внутренние методы без записи в историю
    private void addFirstInternal(int value) {
        if (isFull()) return;

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }

        data[front] = value;
        size++;
    }

    private void addLastInternal(int value) {
        if (isFull()) return;

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = value;
        size++;
    }

    private void removeFirstInternal() {
        if (isEmpty()) return;

        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (front == data.length - 1) {
            front = 0;
            size--;
        } else {
            front++;
            size--;
        }
    }

    private void removeLastInternal() {
        if (isEmpty()) return;

        if (front == rear) {
            front = rear = -1;
            size = 0;
        } else if (rear == 0) {
            rear = data.length - 1;
            size--;
        } else {
            rear--;
            size--;
        }
    }
}
