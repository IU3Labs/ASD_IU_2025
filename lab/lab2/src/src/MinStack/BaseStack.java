// Базовый стек

package MinStack;
import java.util.EmptyStackException;

public class BaseStack {
    protected Node top;
    protected int size;

    protected static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public BaseStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(int value) {
        top = new Node(value, top);
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }
}