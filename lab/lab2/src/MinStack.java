/**
 * Группа А. Задание 1
 * Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
 */

import java.util.*;

public class MinStack<T extends Comparable<T>> {

    private final List<T> mainList;    // Основное хранилище элементов
    private final Stack<T> minStack;   // Стек для отслеживания текущего минимума

    public MinStack() {
        this.mainList = new ArrayList<>();
        this.minStack = new Stack<>();
    }

    /**
     * Стандартное добавление элемента в стек (в конец).
     */
    public void push(T value) {
        mainList.add(value);
        updateMinStackOnPush(value);
        System.out.println("Pushed to end: " + value);
    }

    /**
     * Добавление элемента в начало.
     */
    public void pushToStart(T value) {
        mainList.add(0, value);
        rebuildMinStack();
        System.out.println("Pushed to start: " + value);
    }

    /**
     * Добавление элемента в середину.
     */
    public void pushToMiddle(T value) {
        int middle = mainList.size() / 2;
        mainList.add(middle, value);
        rebuildMinStack();
        System.out.println("Pushed to middle: " + value);
    }

    /**
     * Удаление элемента из конца (стандартный pop).
     */
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();

        T removed = mainList.remove(mainList.size() - 1);
        if (!minStack.isEmpty() && removed.equals(minStack.peek())) {
            minStack.pop();
        }
        return removed;
    }

    /**
     * Удаление из начала.
     */
    public T popFromStart() {
        if (isEmpty()) throw new EmptyStackException();

        T val = mainList.remove(0);
        rebuildMinStack();
        return val;
    }

    /**
     * Удаление из середины.
     */
    public T popFromMiddle() {
        if (isEmpty()) throw new EmptyStackException();

        int mid = mainList.size() / 2;
        T val = mainList.remove(mid);
        rebuildMinStack();
        return val;
    }

    /**
     * Получение минимального элемента за O(1).
     */
    public T getMin() {
        if (minStack.isEmpty()) throw new EmptyStackException();
        return minStack.peek();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return mainList.get(mainList.size() - 1);
    }

    public int size() {
        return mainList.size();
    }

    public boolean isEmpty() {
        return mainList.isEmpty();
    }

    // --- Вспомогательные методы ---

    private void updateMinStackOnPush(T value) {
        if (minStack.isEmpty() || value.compareTo(minStack.peek()) <= 0) {
            minStack.push(value);
        }
    }

    private void rebuildMinStack() {
        minStack.clear();
        if (mainList.isEmpty()) return;

        T currentMin = null;
        for (T value : mainList) {
            if (currentMin == null || value.compareTo(currentMin) <= 0) {
                currentMin = value;
                minStack.push(currentMin);
            }
        }
    }

    /**
     * Печать стека сверху вниз.
     */
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        for (int i = mainList.size() - 1; i >= 0; i--) {
            System.out.print(mainList.get(i) + (i == 0 ? "" : " "));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();

        System.out.println("--- Demonstration ---");
        stack.push(7);
        stack.push(9);
        stack.push(2);
        stack.push(4);
        stack.printStack();
        System.out.println("Min: " + stack.getMin());

        stack.pushToStart(3);
        System.out.println("After pushing to start:");
        stack.printStack();
        System.out.println("Min: " + stack.getMin());

        System.out.println("Popped from middle: " + stack.popFromMiddle());
        stack.printStack();
        System.out.println("Min: " + stack.getMin());
    }
}