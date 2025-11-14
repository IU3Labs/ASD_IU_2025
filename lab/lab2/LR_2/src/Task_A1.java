
// 1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.

import Models.MinStack;

public class Task_A1 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        // проверка логики
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
    }
}