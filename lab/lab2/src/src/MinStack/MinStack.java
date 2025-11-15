// Группа А. Задание 1. Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

package MinStack;
import java.util.EmptyStackException;

public class MinStack extends BaseStack {
    private final BaseStack minStack;

    public MinStack() {
        // Основной стек: хранит все элементы
        super();
        // Стек минимумов: хранит только элементы, которые были/являются минимумом
        this.minStack = new BaseStack();
    }

    /**
     * Добавить value в основной стек
     * Если стек минимумов пуст или value ≤ текущий минимум, добавить value в стек минимумов
     **/
    @Override
    public void push(int value) {
        super.push(value);

        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    /**
     * Удалить элемент из основного стека
     * Если удаленный элемент равен текущему минимуму, удалить элемент из стека минимумов
     **/
    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        int value = super.pop();

        if (value == minStack.peek()) {
            minStack.pop();
        }

        return value;
    }

    // Вернуть верхний элемент стека минимумов (текущий минимум)
    public int getMin() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return minStack.peek();
    }

    @Override
    public void clear() {
        super.clear();
        minStack.clear();
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Стек пуст");
            return;
        }

        System.out.print("Стек: [");
        Node current = top;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println("] (минимум: " + getMin() + ")");
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        // Добавляем элементы
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        stack.push(8);

        // Демонстрируем минимум после каждой операции
        stack.printStack();

        stack.pop(); // удаляем 8
        System.out.println("После удаления 8: ");
        stack.printStack();

        stack.pop(); // удаляем 2
        stack.printStack();
    }
}