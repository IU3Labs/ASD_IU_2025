package Models;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack = new Stack<>(); // основной стек
    private Stack<Integer> minStack = new Stack<>(); // стек для минимумов

    public void push(int x) { // добавление элемента
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
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