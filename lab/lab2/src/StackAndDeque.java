/**
 * Стек с поддержкой операций undo/redo (реализация с нуля)
 */
public class StackAndDeque<T> {
    public static class UndoRedoStack<T> {

        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        /**
         * Абстрактная команда для паттерна Command
         */
        private abstract static class Command {
            abstract void execute();

            abstract void undo();
        }

        /**
         * Команда добавления элемента в стек
         */
        private class PushCommand extends Command {
            private final T element;

            PushCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                Node<T> newNode = new Node<>(element);
                newNode.next = top;
                top = newNode;
                size++;
            }

            @Override
            void undo() {
                if (top != null) {
                    top = top.next;
                    size--;
                }
            }
        }

        /**
         * Команда удаления элемента из стека
         */
        private class PopCommand extends Command {
            private final T element;

            PopCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                if (top != null) {
                    top = top.next;
                    size--;
                }
            }

            @Override
            void undo() {
                Node<T> newNode = new Node<>(element);
                newNode.next = top;
                top = newNode;
                size++;
            }
        }

        private Node<T> top;              // вершина стека
        private int size;                 // размер стека

        // Стеки для команд undo/redo
        private Node<Command> undoTop;
        private Node<Command> redoTop;
        private int undoSize;
        private int redoSize;

        public UndoRedoStack() {
            this.top = null;
            this.size = 0;
            this.undoTop = null;
            this.redoTop = null;
            this.undoSize = 0;
            this.redoSize = 0;
        }

        /**
         * Добавление элемента в стек
         */
        public void push(T element) {
            PushCommand command = new PushCommand(element);
            command.execute();

            // Добавляем команду в стек undo
            Node<Command> newUndoNode = new Node<>(command);
            newUndoNode.next = undoTop;
            undoTop = newUndoNode;
            undoSize++;

            // Очищаем стек redo при новой операции
            clearRedo();
        }

        /**
         * Удаление и возврат верхнего элемента стека
         */
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Стек пуст");
            }

            T element = top.data;
            PopCommand command = new PopCommand(element);
            command.execute();

            // Добавляем команду в стек undo
            Node<Command> newUndoNode = new Node<>(command);
            newUndoNode.next = undoTop;
            undoTop = newUndoNode;
            undoSize++;

            // Очищаем стек redo
            clearRedo();

            return element;
        }

        /**
         * Просмотр верхнего элемента без удаления
         */
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Стек пуст");
            }
            return top.data;
        }

        /**
         * Отмена последней операции
         */
        public void undo() {
            if (!canUndo()) {
                throw new IllegalStateException("Нет операций для отмены");
            }

            Command command = undoTop.data;
            undoTop = undoTop.next;
            undoSize--;

            command.undo();

            // Добавляем команду в стек redo
            Node<Command> newRedoNode = new Node<>(command);
            newRedoNode.next = redoTop;
            redoTop = newRedoNode;
            redoSize++;
        }

        /**
         * Повтор отмененной операции
         */
        public void redo() {
            if (!canRedo()) {
                throw new IllegalStateException("Нет операций для повтора");
            }

            Command command = redoTop.data;
            redoTop = redoTop.next;
            redoSize--;

            command.execute();

            // Добавляем команду обратно в стек undo
            Node<Command> newUndoNode = new Node<>(command);
            newUndoNode.next = undoTop;
            undoTop = newUndoNode;
            undoSize++;
        }

        public boolean canUndo() {
            return undoTop != null;
        }

        public boolean canRedo() {
            return redoTop != null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int size() {
            return size;
        }

        /**
         * Очистка стека redo
         */
        private void clearRedo() {
            redoTop = null;
            redoSize = 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Stack: [");
            Node<T> current = top;
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }
            sb.append("] (size: ").append(size).append(")");
            return sb.toString();
        }
    }


    /**
     * Двусторонняя очередь (Deque) с поддержкой операций undo/redo (реализация с нуля)
     */
    public static class UndoRedoDeque<T> {

        /**
         * Узел для двусвязного списка дека
         */
        private static class Node<T> {
            T data;
            Node<T> prev;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        /**
         * Абстрактная команда для операций с деком
         */
        private abstract static class Command {
            abstract void execute();

            abstract void undo();
        }

        /**
         * Команда добавления в начало
         */
        private class AddFirstCommand extends Command {
            private final T element;

            AddFirstCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                Node<T> newNode = new Node<>(element);
                if (isEmpty()) {
                    front = newNode;
                    rear = newNode;
                } else {
                    newNode.next = front;
                    front.prev = newNode;
                    front = newNode;
                }
                size++;
            }

            @Override
            void undo() {
                if (front != null) {
                    front = front.next;
                    if (front != null) {
                        front.prev = null;
                    } else {
                        rear = null;
                    }
                    size--;
                }
            }
        }

        /**
         * Команда добавления в конец
         */
        private class AddLastCommand extends Command {
            private final T element;

            AddLastCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                Node<T> newNode = new Node<>(element);
                if (isEmpty()) {
                    front = newNode;
                    rear = newNode;
                } else {
                    newNode.prev = rear;
                    rear.next = newNode;
                    rear = newNode;
                }
                size++;
            }

            @Override
            void undo() {
                if (rear != null) {
                    rear = rear.prev;
                    if (rear != null) {
                        rear.next = null;
                    } else {
                        front = null;
                    }
                    size--;
                }
            }
        }

        /**
         * Команда удаления из начала
         */
        private class RemoveFirstCommand extends Command {
            private final T element;

            RemoveFirstCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                if (front != null) {
                    front = front.next;
                    if (front != null) {
                        front.prev = null;
                    } else {
                        rear = null;
                    }
                    size--;
                }
            }

            @Override
            void undo() {
                Node<T> newNode = new Node<>(element);
                if (isEmpty()) {
                    front = newNode;
                    rear = newNode;
                } else {
                    newNode.next = front;
                    front.prev = newNode;
                    front = newNode;
                }
                size++;
            }
        }

        /**
         * Команда удаления из конца
         */
        private class RemoveLastCommand extends Command {
            private final T element;

            RemoveLastCommand(T element) {
                this.element = element;
            }

            @Override
            void execute() {
                if (rear != null) {
                    rear = rear.prev;
                    if (rear != null) {
                        rear.next = null;
                    } else {
                        front = null;
                    }
                    size--;
                }
            }

            @Override
            void undo() {
                Node<T> newNode = new Node<>(element);
                if (isEmpty()) {
                    front = newNode;
                    rear = newNode;
                } else {
                    newNode.prev = rear;
                    rear.next = newNode;
                    rear = newNode;
                }
                size++;
            }
        }

        private Node<T> front;            // начало дека
        private Node<T> rear;             // конец дека
        private int size;                 // размер дека

        // Стеки для команд undo/redo
        private Node<Command> undoTop;
        private Node<Command> redoTop;
        private int undoSize;
        private int redoSize;

        public UndoRedoDeque() {
            this.front = null;
            this.rear = null;
            this.size = 0;
            this.undoTop = null;
            this.redoTop = null;
            this.undoSize = 0;
            this.redoSize = 0;
        }

        // Основные операции дека

        public void addFirst(T element) {
            AddFirstCommand command = new AddFirstCommand(element);
            command.execute();

            addToUndoStack(command);
            clearRedo();
        }

        public void addLast(T element) {
            AddLastCommand command = new AddLastCommand(element);
            command.execute();

            addToUndoStack(command);
            clearRedo();
        }

        public T removeFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("Дек пуст");
            }

            T element = front.data;
            RemoveFirstCommand command = new RemoveFirstCommand(element);
            command.execute();

            addToUndoStack(command);
            clearRedo();

            return element;
        }

        public T removeLast() {
            if (isEmpty()) {
                throw new IllegalStateException("Дек пуст");
            }

            T element = rear.data;
            RemoveLastCommand command = new RemoveLastCommand(element);
            command.execute();

            addToUndoStack(command);
            clearRedo();

            return element;
        }

        public T getFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("Дек пуст");
            }
            return front.data;
        }

        public T getLast() {
            if (isEmpty()) {
                throw new IllegalStateException("Дек пуст");
            }
            return rear.data;
        }

        // Операции undo/redo

        public void undo() {
            if (!canUndo()) {
                throw new IllegalStateException("Нет операций для отмены");
            }

            Command command = undoTop.data;
            undoTop = undoTop.next;
            undoSize--;

            command.undo();

            addToRedoStack(command);
        }

        public void redo() {
            if (!canRedo()) {
                throw new IllegalStateException("Нет операций для повтора");
            }

            Command command = redoTop.data;
            redoTop = redoTop.next;
            redoSize--;

            command.execute();

            addToUndoStack(command);
        }

        public boolean canUndo() {
            return undoTop != null;
        }

        public boolean canRedo() {
            return redoTop != null;
        }

        public boolean isEmpty() {
            return front == null;
        }

        public int size() {
            return size;
        }

        // Вспомогательные методы

        private void addToUndoStack(Command command) {
            Node<Command> newNode = new Node<>(command);
            newNode.next = undoTop;
            undoTop = newNode;
            undoSize++;
        }

        private void addToRedoStack(Command command) {
            Node<Command> newNode = new Node<>(command);
            newNode.next = redoTop;
            redoTop = newNode;
            redoSize++;
        }

        private void clearRedo() {
            redoTop = null;
            redoSize = 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Deque: [");
            Node<T> current = front;
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(", ");
                }
                current = current.next;
            }
            sb.append("] (size: ").append(size).append(")");
            return sb.toString();
        }
    }
}
