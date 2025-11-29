package lab2.models;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>(); // дополнительный стек для хранения минимумов

    public void push(int x) {
        mainStack.push(x);
        // если minStack пустой или новый элемент меньше или равен текущему минимуму, добавляем его
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        // если удаляемый элемент равен текущему минимуму, удаляем и из minStack
        if (mainStack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}