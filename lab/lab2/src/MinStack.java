/* Реализовать «Стек с минимумом» (Min Stack)*/
import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.add(92);
        stack.add(122);
        stack.add(43);
        stack.add(-124);
        stack.add(5);
        System.out.println("current min" + stack.curMin());
        stack.removeTop();
        System.out.println("new min " + stack.curMin());
        System.out.println("top element " + stack.peekTop());
    }

    private Stack<Integer> stack; 
    private Stack<Integer> minStack;     

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public int curMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        return minStack.peek();
    }

    public void add(int value) {
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public void removeTop() {
        if (stack.isEmpty()) {
            System.out.println("empty stack");
            return;
        }
        int removed = stack.pop();
        if (!minStack.isEmpty() && removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int peekTop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        return stack.peek();
    }
}