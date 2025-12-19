//Группа Б Реализовать свой «Стек» и «Двустороннюю очередь» с поддержкой
//операций undo/redo. Прокомментировать код.
import java.util.*;

public class UndoRedoManager<T> {
    private interface Operation<T> {
        void perform();
        void reverse();
    }

    private final List<T> data;
    private final Stack<Operation<T>> history;     // История для undo
    private final Stack<Operation<T>> future;      // История для redo

    // Флаг для определения типа структуры
    private final boolean isStack;  // true - стек, false - очередь

    public UndoRedoManager(boolean isStack) {
        this.isStack = isStack;
        this.data = isStack ? new Stack<T>() : new LinkedList<T>();
        this.history = new Stack<>();
        this.future = new Stack<>();
    }

    //для стека

    public void pushToStack(T element) {
        ((Stack<T>)data).push(element);

        history.push(new Operation<T>() {
            public void perform() { }
            public void reverse() {
                ((Stack<T>)data).pop();
            }
        });

        future.clear();
    }

    public T popFromStack() {
        if (data.isEmpty()) return null;

        T element = ((Stack<T>)data).pop();

        history.push(new Operation<T>() {
            public void perform() {  }
            public void reverse() {
                ((Stack<T>)data).push(element);
            }
        });

        future.clear();
        return element;
    }

    public T peekStack() {
        return data.isEmpty() ? null : ((Stack<T>)data).peek();
    }

    //для двухсторонней очереди

    public void addToFront(T element) {
        ((LinkedList<T>)data).addFirst(element);

        history.push(new Operation<T>() {
            public void perform() {  }
            public void reverse() {
                ((LinkedList<T>)data).removeFirst();
            }
        });

        future.clear();
    }

    public void addToBack(T element) {
        ((LinkedList<T>)data).addLast(element);

        history.push(new Operation<T>() {
            public void perform() { }
            public void reverse() {
                ((LinkedList<T>)data).removeLast();
            }
        });

        future.clear();
    }

    public T removeFromFront() {
        if (data.isEmpty()) return null;

        T element = ((LinkedList<T>)data).removeFirst();

        history.push(new Operation<T>() {
            public void perform() { }
            public void reverse() {
                ((LinkedList<T>)data).addFirst(element);
            }
        });

        future.clear();
        return element;
    }

    public T removeFromBack() {
        if (data.isEmpty()) return null;

        T element = ((LinkedList<T>)data).removeLast();

        history.push(new Operation<T>() {
            public void perform() { }
            public void reverse() {
                ((LinkedList<T>)data).addLast(element);
            }
        });

        future.clear();
        return element;
    }

    public T peekFront() {
        return data.isEmpty() ? null : ((LinkedList<T>)data).getFirst();
    }

    public T peekBack() {
        return data.isEmpty() ? null : ((LinkedList<T>)data).getLast();
    }

    // undo/redo

    public boolean undo() {
        if (history.isEmpty()) return false;

        Operation<T> operation = history.pop();
        operation.reverse();

        future.push(operation);
        return true;
    }

    public boolean redo() {
        if (future.isEmpty()) return false;

        Operation<T> operation = future.pop();

        history.push(new Operation<T>() {
            public void perform() { }
            public void reverse() {
                operation.reverse();
            }
        });

        operation.reverse();
        return true;
    }

    //вспомогательные методы

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
        history.clear();
        future.clear();
    }

    public void display() {
        System.out.print(isStack ? "Стек: " : "Очередь: ");
        System.out.println(data);
    }

    public static void main(String[] args) {
        System.out.println("Тестирование стека");
        UndoRedoManager<Integer> stack = new UndoRedoManager<>(true);

        System.out.println("\nДобавляем элементы в стек:");
        stack.pushToStack(1);
        stack.pushToStack(2);
        stack.pushToStack(3);
        stack.display();

        System.out.println("\nУдаляем элемент из стека:");
        stack.popFromStack();
        stack.display();

        System.out.println("\nОтменяем последнюю операцию (Undo):");
        stack.undo();
        stack.display();

        System.out.println("\nПовторяем операцию (Redo):");
        stack.redo();
        stack.display();

        System.out.println("\nОтменяем две операции подряд:");
        stack.undo();
        stack.undo();
        stack.display();

        System.out.println("\nВерхний элемент стека: " + stack.peekStack());

        System.out.println("\nТестирование очереди");
        UndoRedoManager<String> queue = new UndoRedoManager<>(false);

        System.out.println("\nДобавляем элементы в очередь:");
        queue.addToFront("A");
        queue.addToBack("B");
        queue.addToFront("C");
        queue.display();

        System.out.println("\nУдаляем из начала очереди:");
        queue.removeFromFront();
        queue.display();

        System.out.println("\nДобавляем в конец очереди:");
        queue.addToBack("D");
        queue.display();

        System.out.println("\nОтменяем две операции:");
        queue.undo();
        queue.undo();
        queue.display();

        System.out.println("\nПовторяем одну операцию:");
        queue.redo();
        queue.display();

        System.out.println("\nПервый элемент: " + queue.peekFront());
        System.out.println("Последний элемент: " + queue.peekBack());
    }
}