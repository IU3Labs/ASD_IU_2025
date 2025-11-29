package lab2.src;

import lab2.models.MinStack;


public class TaskA1 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);

        System.out.println("Минимум: " + stack.getMin());

        stack.pop();
        System.out.println("Минимум после pop: " + stack.getMin());
        System.out.println("Верхний элемент: " + stack.top());

        stack.push(1);
        System.out.println("Минимум после push(1): " + stack.getMin());

        stack.pop();
        System.out.println("Минимум после еще одного pop: " + stack.getMin());
    }
}