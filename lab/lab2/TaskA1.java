// 1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

import models.MinStack;

public class TaskA1 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        // проверка логики
        stack.push(10);
        stack.push(6);
        stack.push(14);
        stack.push(4);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
    }
}