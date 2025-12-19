import java.util.*;
//1 Реализовать «Стек с минимумом» (Min Stack). Прокомментировать логику.
public class minstack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public minstack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);

        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int value = stack.pop();

            if (value == minStack.peek()) {
                minStack.pop();
            }
        }
    }


    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return -1;
    }

    public static void main(String[] args) {
        minstack minStack = new minstack();

        minStack.push(3);
        minStack.push(5);
        System.out.println(minStack.getMin());
        minStack.push(2);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
