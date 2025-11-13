import java.util.Stack;

/**
 * Стек с поддержкой операций Undo/Redo
 */
class UndoRedoStack<T> {
    private final Stack<T> stack;          // Основной стек
    private final Stack<T> undoStack;      // Стек для отмененных элементов
    private final Stack<Command> commandHistory; // История команд для сложных операций

    public UndoRedoStack() {
        this.stack = new Stack<>();
        this.undoStack = new Stack<>();
        this.commandHistory = new Stack<>();
    }

    /**
     * Команда для хранения операции и данных для undo/redo
     */
    private interface Command {
        void undo();
        void redo();
    }

    // Push команда
    private class PushCommand implements Command {
        private final T item;

        PushCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            T popped = stack.pop();
            undoStack.push(popped);
        }

        @Override
        public void redo() {
            stack.push(item);
            undoStack.pop();
        }
    }

    // Pop команда
    private class PopCommand implements Command {
        private final T item;

        PopCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            stack.push(item);
        }

        @Override
        public void redo() {
            stack.pop();
        }
    }

    /**
     * Добавление элемента с записью команды
     */
    public void push(T item) {
        stack.push(item);
        commandHistory.push(new PushCommand(item));
        // Очищаем redo stack при новой операции
        undoStack.clear();
    }

    /**
     * Удаление элемента с записью команды
     */
    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = stack.pop();
        commandHistory.push(new PopCommand(item));
        undoStack.push(item);
        return item;
    }

    /**
     * Отмена последней операции
     */
    public void undo() {
        if (commandHistory.isEmpty()) {
            throw new IllegalStateException("Nothing to undo");
        }
        Command command = commandHistory.pop();
        command.undo();
    }

    /**
     * Повтор отмененной операции
     */
    public void redo() {
        if (undoStack.isEmpty()) {
            throw new IllegalStateException("Nothing to redo");
        }
        // Для простоты - повторяем последнюю отмененную операцию pop
        T item = undoStack.pop();
        stack.push(item);
    }

    public T peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void printStack() {
        System.out.println("Stack: " + stack);
        System.out.println("Undo Stack: " + undoStack);
    }
}

/**
 * Двусторонняя очередь (Deque) с поддержкой операций Undo/Redo
 */
class UndoRedoDeque<T> {
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    // Стеки для истории команд
    private final Stack<Command> commandHistory = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Интерфейс команды для undo/redo
     */
    private interface Command {
        void undo();
        void redo();
    }

    // Команда добавления в начало
    private class AddFirstCommand implements Command {
        private final T item;

        AddFirstCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            removeFirst();
        }

        @Override
        public void redo() {
            addFirst(item);
        }
    }

    // Команда добавления в конец
    private class AddLastCommand implements Command {
        private final T item;

        AddLastCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            removeLast();
        }

        @Override
        public void redo() {
            addLast(item);
        }
    }

    // Команда удаления из начала
    private class RemoveFirstCommand implements Command {
        private final T item;

        RemoveFirstCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            addFirst(item);
        }

        @Override
        public void redo() {
            removeFirst();
        }
    }

    // Команда удаления из конца
    private class RemoveLastCommand implements Command {
        private final T item;

        RemoveLastCommand(T item) {
            this.item = item;
        }

        @Override
        public void undo() {
            addLast(item);
        }

        @Override
        public void redo() {
            removeLast();
        }
    }

    public UndoRedoDeque() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Добавление в начало с записью команды
     */
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;

        // Записываем команду
        commandHistory.push(new AddFirstCommand(item));
        redoStack.clear();
    }

    /**
     * Добавление в конец с записью команды
     */
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;

        // Записываем команду
        commandHistory.push(new AddLastCommand(item));
        redoStack.clear();
    }

    /**
     * Удаление из начала с записью команды
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        T item = front.data;

        if (front == rear) {
            front = rear = null;
        } else {
            front = front.next;
            front.prev = null;
        }
        size--;

        // Записываем команду
        commandHistory.push(new RemoveFirstCommand(item));
        return item;
    }

    /**
     * Удаление из конца с записью команды
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        T item = rear.data;

        if (front == rear) {
            front = rear = null;
        } else {
            rear = rear.prev;
            rear.next = null;
        }
        size--;

        // Записываем команду
        commandHistory.push(new RemoveLastCommand(item));
        return item;
    }

    /**
     * Отмена последней операции
     */
    public void undo() {
        if (commandHistory.isEmpty()) {
            throw new IllegalStateException("Nothing to undo");
        }

        Command command = commandHistory.pop();
        command.undo();
        redoStack.push(command);
    }

    /**
     * Повтор отмененной операции
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            throw new IllegalStateException("Nothing to redo");
        }

        Command command = redoStack.pop();
        command.redo();
        commandHistory.push(command);
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return front.data;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return rear.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Печать deque в прямом порядке
     */
    public void printDeque() {
        System.out.print("Deque (front -> rear): ");
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Печать deque в обратном порядке
     */
    public void printDequeReverse() {
        System.out.print("Deque (rear -> front): ");
        Node<T> current = rear;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }
}
