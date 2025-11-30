package models;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack = new Stack<>(); // основной стек
    private Stack<Integer> minStack = new Stack<>(); // стек для минимумов

    public void push(int value) { // добавление элемента
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() { // удаление верхнего элемента
        int removed = stack.pop();
        if (!minStack.isEmpty() && removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() { // вернуть верхний элемент
        return stack.peek();
    }

    public int getMin() { // вернуть текущий минимум
        return minStack.peek();
    }
}